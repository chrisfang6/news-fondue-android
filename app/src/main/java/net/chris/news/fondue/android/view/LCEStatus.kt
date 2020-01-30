package net.chris.news.fondue.android.view

interface LCEStatus<T> {

    fun isLoading(): Boolean

    fun error(): Int

    fun content(): T
}
