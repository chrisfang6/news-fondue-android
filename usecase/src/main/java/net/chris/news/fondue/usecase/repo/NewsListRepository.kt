package net.chris.news.fondue.usecase.repo

import io.reactivex.Single
import net.chris.news.fondue.usecase.NewsType
import net.chris.news.fondue.usecase.bo.NewsBO

interface NewsListRepository {

    fun getNewsList(
        category: String,
        type: NewsType,
        requestedLoadSize: Int
    ): Single<List<NewsBO>>

    fun getNewsListAfter(
        category: String,
        afterDocId: String?,
        type: NewsType,
        requestedLoadSize: Int
    ): Single<List<NewsBO>>

    fun getNewsListBefore(
        category: String,
        beforeDocId: String,
        type: NewsType,
        requestedLoadSize: Int
    ): Single<List<NewsBO>>
}
