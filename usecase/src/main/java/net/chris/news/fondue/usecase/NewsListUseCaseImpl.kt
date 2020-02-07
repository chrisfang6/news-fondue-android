package net.chris.news.fondue.usecase

import net.chris.news.fondue.usecase.NewsType.HEADLINES
import net.chris.news.fondue.usecase.callback.ResultListener
import net.chris.news.fondue.usecase.repo.NewsListRepository
import javax.inject.Inject

class NewsListUseCaseImpl @Inject constructor(
    private val newsListRepository: NewsListRepository
) : NewsListUseCase {

    override fun getHeadlines(
        category: String,
        startIndex: Int,
        listener: ResultListener
    ) = newsListRepository.getNewsList(category, startIndex, listener, HEADLINES)
}
