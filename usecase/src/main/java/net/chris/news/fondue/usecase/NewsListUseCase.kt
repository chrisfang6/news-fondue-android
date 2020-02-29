package net.chris.news.fondue.usecase

import io.reactivex.Single
import net.chris.news.fondue.usecase.bo.NewsBO

interface NewsListUseCase {

    fun getTopHeadlines(requestLoadSize: Int): Single<List<NewsBO>>

    fun getMoreHeadlines(
        after: String,
        requestedLoadSize: Int
    ): Single<List<NewsBO>>

    fun getBeforeHeadlines(
        before: String,
        requestedLoadSize: Int
    ): Single<List<NewsBO>>
}
