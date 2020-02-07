package net.chris.news.fondue.usecase

import net.chris.news.fondue.usecase.bo.HeadlinesBO
import net.chris.news.fondue.usecase.callback.ResultListener

interface NewsListUseCase {

    fun getHeadlines(
        category: String,
        startIndex: Int,
        listener: ResultListener<HeadlinesBO>
    )
}
