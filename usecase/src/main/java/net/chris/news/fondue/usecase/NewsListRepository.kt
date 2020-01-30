package net.chris.news.fondue.usecase

interface NewsListRepository {

    fun <T> getNewsList(
        category: String,
        startIndex: Int,
        listener: ResultListener<T>,
        clazz: Class<T>
    )
}
