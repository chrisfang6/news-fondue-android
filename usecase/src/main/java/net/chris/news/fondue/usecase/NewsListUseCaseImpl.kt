package net.chris.news.fondue.usecase

import javax.inject.Inject

class NewsListUseCaseImpl @Inject constructor(
    private val newsListRepository: NewsListRepository
) : NewsListUseCase {

    override fun getHeadlines(
        category: String,
        startIndex: Int,
        listener: ResultListener<Headlines>
    ) = newsListRepository.getNewsList(category, startIndex, listener, Headlines::class.java)
}
