package net.chris.news.fondue.repository

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import net.chris.news.fondue.usecase.Headlines
import net.chris.news.fondue.usecase.NewsListRepository
import net.chris.news.fondue.usecase.ResultListener
import javax.inject.Inject

class NewsListRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val compositeDisposable: CompositeDisposable
) : NewsListRepository {

    override fun <T> getNewsList(
        category: String,
        startIndex: Int,
        listener: ResultListener<T>,
        clazz: Class<T>
    ) {
        when (clazz.name) {
            Headlines::class.java.name -> {
                compositeDisposable.add(newsApi.getRealTimeHot(category, startIndex)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        { listener.onResult(it as T) },
                        { listener.onError(it) }
                    )
                )
            }
        }
    }
}
