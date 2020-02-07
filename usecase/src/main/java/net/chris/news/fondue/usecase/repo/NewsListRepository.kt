package net.chris.news.fondue.usecase.repo

import net.chris.news.fondue.usecase.callback.ResultListener

interface NewsListRepository {

    fun <T> getNewsList(
        category: String,
        startIndex: Int,
        listener: ResultListener<T>,
        clazz: Class<T>
    )
}
