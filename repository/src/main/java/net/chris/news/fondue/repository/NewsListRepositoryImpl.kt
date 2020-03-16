/**
 *   Copyright 2019 chrisfang6
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package net.chris.news.fondue.repository

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import net.chris.news.fondue.repository.Constant.MAX_TRY
import net.chris.news.fondue.repository.Constant.SIZE_PER_REQUEST
import net.chris.news.fondue.repository.converter.NewsDO2POConverter
import net.chris.news.fondue.repository.converter.NewsPO2BOConverter
import net.chris.news.fondue.repository.network.NewsApi
import net.chris.news.fondue.repository.po.NewsPO
import net.chris.news.fondue.usecase.NewsType
import net.chris.news.fondue.usecase.NewsType.HEADLINES
import net.chris.news.fondue.usecase.bo.HeadlinesBO
import net.chris.news.fondue.usecase.bo.NewsBO
import net.chris.news.fondue.usecase.repo.NewsListRepository
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.ceil

class NewsListRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDO2POConverter: NewsDO2POConverter,
    private val newsPO2BOConverter: NewsPO2BOConverter,
    private val newsDatabaseHandler: NewsDatabaseHandler
) : NewsListRepository {

    private var fromIndexForAfter = 0

    override fun getNewsList(
        category: String,
        type: NewsType,
        requestedLoadSize: Int
    ): Single<List<NewsBO>> = getNewsListAfter(category, null, type, requestedLoadSize)

    override fun getNewsListAfter(
        category: String,
        afterDocId: String?,
        type: NewsType,
        requestedLoadSize: Int
    ): Single<List<NewsBO>> = when (type) {
        HEADLINES -> {
            Timber.d("##########")
            Timber.d(afterDocId?.let { "# To get $requestedLoadSize news AFTER $afterDocId" } ?: "# Get $requestedLoadSize TOP news")
            Timber.d("##########")
            // Try so many times, then for the efficiency we would give up even if we can't get the enough news.
            val shouldTry = MAX_TRY.coerceAtLeast(ceil(requestedLoadSize.toDouble() / SIZE_PER_REQUEST).toInt())
            var tried = 0
            var fromIndex = fromIndexForAfter
            fetchLocalNewsAfter(afterDocId, requestedLoadSize, type)
                .subscribeOn(Schedulers.io())
                .flatMapObservable {
                    when {
                        it.size < requestedLoadSize && tried < shouldTry -> {
                            Timber.d("# Got ${it.size} records from database less than requested number of $requestedLoadSize")
                            Observable.error(Exception("Not so many local records"))
                        }
                        else -> {
                            Observable.just(it)
                        }
                    }
                }
                .retryWhen {
                    it.flatMap {
                        fetchNetworkNewsSingle(fromIndex, category)
                            .flatMapObservable { headlines -> saveNewsObservable(headlines, type) }
                            .doOnNext {
                                fromIndex += SIZE_PER_REQUEST
                                fromIndexForAfter = fromIndex
                                tried += 1
                            }
                    }
                }
                .flatMap { Observable.fromIterable(it) }
                .map { newsPO2BOConverter.apply(it) }
                .toList()
        }
        else -> {
            Single.error<List<NewsBO>>(IllegalArgumentException("unknown news type"))
        }
    }

    override fun getNewsListBefore(
        category: String,
        beforeDocId: String,
        type: NewsType,
        requestedLoadSize: Int
    ): Single<List<NewsBO>> = when (type) {
        HEADLINES -> {
            Timber.d("##########")
            Timber.d("# To get news $requestedLoadSize BEFORE $beforeDocId")
            Timber.d("##########")
            // Try MAX_TRY times requests, and then for the efficiency we would give up even if we can't touch the top news of current list.
            val shouldTry = MAX_TRY
            var tried = 0
            var touched = false // If it has got the news including the top news of current list.
            var fromIndex = 0
            fetchLocalNewsBefore(beforeDocId, requestedLoadSize, type)
                .subscribeOn(Schedulers.io())
                .flatMapObservable { list ->
                    Timber.d("# ${list.size} local news before $beforeDocId, tried $tried times, and ${if (!touched) "no" else ""} touched")
                    when {
                        // Matched news items are already in local storage.
                        // Only for the first time without retrying.
                        (list.size == requestedLoadSize && tried == 0) ||
                            // Touch the top news of current list from network, no matter how many times it has tried.
                            touched ||
                            // Tried too many times
                            tried >= shouldTry -> {
                            Observable.just(list)
                        }
                        else -> Observable.error(Exception("No enough local records got"))
                    }
                }
                .retryWhen {
                    it.flatMap {
                        fetchNetworkNewsSingle(fromIndex, category)
                            .flatMapObservable { headlines -> saveNewsObservable(headlines, type) }
                            .doOnNext { headlines ->
                                headlines.find { item -> item.docId == beforeDocId }?.let { touched = true }
                                    ?: {
                                        fromIndex += SIZE_PER_REQUEST
                                        tried += 1
                                    }()
                            }
                    }
                }
                .flatMap { Observable.fromIterable(it) }
                .map { newsPO2BOConverter.apply(it) }
                .toList()
        }
        else -> {
            Single.error<List<NewsBO>>(IllegalArgumentException("unknown news type"))
        }
    }

    private fun fetchLocalNewsBefore(
        beforeDocId: String,
        requestedLoadSize: Int,
        type: NewsType
    ) = Single.fromCallable<List<NewsPO>> {
        newsDatabaseHandler.getAllNewsBefore(
            type.name,
            beforeDocId,
            requestedLoadSize
        )?.reversed() ?: listOf()
    }

    private fun fetchLocalNewsAfter(
        afterDocId: String?,
        requestedLoadSize: Int,
        type: NewsType
    ) = Single.fromCallable<List<NewsPO>> {
        (afterDocId?.let {
            newsDatabaseHandler.getAllNewsAfter(
                type.name,
                afterDocId,
                requestedLoadSize
            )
        } ?: newsDatabaseHandler.getAllNews(
            type.name,
            requestedLoadSize
        )) ?: listOf()
    }

    private fun saveNewsObservable(
        headlinesBO: HeadlinesBO,
        type: NewsType
    ): Observable<out List<NewsPO>> = Single.just(headlinesBO)
        .flatMapObservable { Observable.fromIterable(it.list) }
        .filter { it.docid?.isNotBlank() == true || it.title?.isNotBlank() == true || it.ptime?.isNotBlank() == true }
        .doOnNext { it.tname = NewsType.convert(type.name).toString() } // Fix the null value bug of the api.
        .map {
            Timber.d("# NewsDO: $it")
            newsDO2POConverter.apply(it)
        }
        .toList()
        .toObservable()
        .doOnNext { newsDatabaseHandler.insertAll(*it.toTypedArray()) }

    private fun fetchNetworkNewsSingle(
        fromIndex: Int,
        category: String
    ) = newsApi.fetchHeadlines(category, fromIndex)
}
