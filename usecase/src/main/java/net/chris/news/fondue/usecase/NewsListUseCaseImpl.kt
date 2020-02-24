package net.chris.news.fondue.usecase

import io.reactivex.Single
import net.chris.news.fondue.usecase.NewsType.HEADLINES
import net.chris.news.fondue.usecase.bo.NewsBO
import net.chris.news.fondue.usecase.repo.NewsListRepository
import javax.inject.Inject

class NewsListUseCaseImpl @Inject constructor(
    private val newsListRepository: NewsListRepository
) : NewsListUseCase {

    override fun getTopHeadlines(
        requestedLoadSize: Int
    ): Single<List<NewsBO>> = newsListRepository.getNewsList("T1348647853363", HEADLINES, requestedLoadSize)

    override fun getMoreHeadlines(
        afterId: String,
        requestedLoadSize: Int
    ): Single<List<NewsBO>> = newsListRepository.getNewsListAfter("T1348647853363", afterId, HEADLINES, requestedLoadSize)

    override fun getBeforeHeadlines(
        beforeId: String,
        requestedLoadSize: Int
    ): Single<List<NewsBO>> = newsListRepository.getNewsListBefore("T1348647853363", beforeId, HEADLINES, requestedLoadSize)
}
