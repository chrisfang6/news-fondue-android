package net.chris.news.fondue.usecase

interface NewsListUseCase {

    fun getHeadlines(
        category: String,
        startIndex: Int,
        listener: ResultListener<Headlines>
    )
}
