package net.chris.news.fondue.android.viewmodel

import androidx.lifecycle.ViewModel
import net.chris.news.fondue.usecase.bo.HeadlinesBO
import net.chris.news.fondue.usecase.NewsListUseCase
import net.chris.news.fondue.usecase.callback.ResultListener
import timber.log.Timber
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val newsListUseCase: NewsListUseCase
) : ViewModel() {

    fun request() {
        newsListUseCase.getHeadlines(
            "T1348647853363",
            0,
            object : ResultListener<HeadlinesBO> {
                override fun onResult(content: HeadlinesBO) {
                    Timber.d("got ${content.list.size} news")
                }

                override fun onError(error: Throwable) {
                    Timber.e(error)
                }
            })
    }
}
