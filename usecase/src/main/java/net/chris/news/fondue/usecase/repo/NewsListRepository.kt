package net.chris.news.fondue.usecase.repo

import net.chris.news.fondue.usecase.NewsType
import net.chris.news.fondue.usecase.callback.ResultListener

interface NewsListRepository {

    fun getNewsList(
        category: String,
        startIndex: Int,
        listener: ResultListener,
        type: NewsType
    )
}
