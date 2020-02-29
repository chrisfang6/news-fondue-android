package net.chris.news.fondue.android.vo

import org.joda.time.DateTime

data class NewsVO(
    val digest: String?,
    val docId: String,
    val favorite: Boolean = false,
    val imageSrc: String?,
    val postTime: DateTime,
    val source: String?,
    val title: String,
    val type: String,
    val url: String?
)
