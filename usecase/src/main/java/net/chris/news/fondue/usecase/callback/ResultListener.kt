package net.chris.news.fondue.usecase.callback

interface ResultListener {

    fun onSuccess()

    fun onError(error: Throwable)
}
