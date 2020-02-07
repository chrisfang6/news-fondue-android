package net.chris.news.fondue.usecase

import com.google.gson.annotations.SerializedName
import net.chris.news.fondue.domain.NewsEntity

data class Headlines(@SerializedName("T1348647853363") val list: List<NewsEntity> = listOf())
