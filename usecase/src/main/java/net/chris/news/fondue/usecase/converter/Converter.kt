package net.chris.news.fondue.usecase.converter

interface Converter<T, K> {

    fun apply(t: T): K
}
