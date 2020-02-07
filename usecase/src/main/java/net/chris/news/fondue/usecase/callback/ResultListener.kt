package net.chris.news.fondue.usecase.callback

interface ResultListener<T> {

    fun onResult(content: T)

    fun onError(error: Throwable)
}
