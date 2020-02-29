package net.chris.news.fondue.android.paging

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import net.chris.news.fondue.android.converter.NewsBO2VOConverter
import net.chris.news.fondue.android.vo.NewsVO
import net.chris.news.fondue.usecase.NewsListUseCase
import net.chris.news.fondue.usecase.bo.NewsBO
import timber.log.Timber
import javax.inject.Inject

class HeadlinesDataSourceImpl @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val newsListUseCase: NewsListUseCase,
    private val converter: NewsBO2VOConverter
) : HeadlinesDataSource() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<NewsVO>) {
        compositeDisposable.add(
            requestHeadlinesSingle(requestTop(params.requestedLoadSize))
                .observeOn(mainThread())
                .subscribe(
                    {
                        Timber.d("# Initial: Got ${it.size} news")
                        callback.onResult(it)
                    },
                    { Timber.e(it) }
                )
        )
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<NewsVO>) {
        compositeDisposable.add(
            requestHeadlinesSingle(requestMore(params))
                .observeOn(mainThread())
                .subscribe(
                    {
                        Timber.d("# Load more ${it.size} news after ${params.key}")
                        callback.onResult(it)
                    },
                    { Timber.e(it) }
                )
        )
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<NewsVO>) {
        compositeDisposable.add(
            requestHeadlinesSingle(requestBefore(params))
                .observeOn(mainThread())
                .subscribe(
                    {
                        Timber.d("# Load ${it.size} news before ${params.key}")
                        callback.onResult(it)
                    },
                    { Timber.e(it) }
                )
        )
    }

    override fun getKey(item: NewsVO) = item.docId

    private fun requestHeadlinesSingle(requestHeadlinesSingle: Single<List<NewsBO>>): Single<List<NewsVO>> {
        return requestHeadlinesSingle
            .flatMapObservable { Observable.fromIterable(it) }
            .map { converter.apply(it) }
            .toList()
    }

    private fun requestTop(requestedLoadSize: Int): Single<List<NewsBO>> =
        newsListUseCase.getTopHeadlines(requestedLoadSize)

    private fun requestMore(params: LoadParams<String>): Single<List<NewsBO>> =
        newsListUseCase.getMoreHeadlines(params.key, params.requestedLoadSize)

    private fun requestBefore(params: LoadParams<String>): Single<List<NewsBO>> =
        newsListUseCase.getBeforeHeadlines(params.key, params.requestedLoadSize)
}
