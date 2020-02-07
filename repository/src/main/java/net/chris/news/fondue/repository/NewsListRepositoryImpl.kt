package net.chris.news.fondue.repository

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import net.chris.news.fondue.repository.converter.NewsPersistentConverter
import net.chris.news.fondue.repository.dao.NewsDAO
import net.chris.news.fondue.repository.network.NewsApi
import net.chris.news.fondue.repository.po.NewsPO
import net.chris.news.fondue.usecase.NewsType
import net.chris.news.fondue.usecase.NewsType.HEADLINES
import net.chris.news.fondue.usecase.callback.ResultListener
import net.chris.news.fondue.usecase.repo.NewsListRepository
import javax.inject.Inject

class NewsListRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val compositeDisposable: CompositeDisposable,
    private val newsPersistentConverter: NewsPersistentConverter,
    private val newsDAO: NewsDAO
) : NewsListRepository {

    override fun getNewsList(
        category: String,
        startIndex: Int,
        listener: ResultListener,
        type: NewsType
    ) {
        compositeDisposable.add(
            when (type) {
                HEADLINES -> {
                    Single.fromObservable(
                        newsApi.fetchHeadlines(category, startIndex)
                            .subscribeOn(Schedulers.io())
                            .flatMapObservable { Observable.fromIterable(it.list) }
                            .filter { it.docid?.isNotBlank() == true || it.title?.isNotBlank() == true || it.ptime?.isNotBlank() == true }
                            .map { newsPersistentConverter.apply(it, HEADLINES) }
                            .toList()
                            .toObservable()
                            .doOnNext { newsDAO.insertAll(*(it as List<NewsPO>).toTypedArray()) }
                    )
                }
                else -> {
                    Single.error<Unit>(IllegalArgumentException("unknown news type"))
                }
            }
                .subscribe(
                    { listener.onSuccess() },
                    { listener.onError(it) }
                )
        )
    }
}
