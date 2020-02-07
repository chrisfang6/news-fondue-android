package net.chris.news.fondue.repository.converter

interface Converter<T, K, R> {

    fun apply(t: T, k: K): R
}
