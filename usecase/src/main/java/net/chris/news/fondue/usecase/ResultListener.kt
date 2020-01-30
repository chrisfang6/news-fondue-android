package net.chris.news.fondue.usecase

interface ResultListener<T> {

    fun onResult(content: T)

    fun onError(error: Throwable)
}
