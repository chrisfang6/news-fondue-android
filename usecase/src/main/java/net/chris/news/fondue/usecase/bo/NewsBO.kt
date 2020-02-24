package net.chris.news.fondue.usecase.bo

import org.joda.time.DateTime

data class NewsBO(
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
