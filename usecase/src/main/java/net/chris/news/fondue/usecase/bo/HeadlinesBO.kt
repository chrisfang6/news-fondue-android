package net.chris.news.fondue.usecase.bo

import com.google.gson.annotations.SerializedName
import net.chris.news.fondue.domain.NewsDO

data class HeadlinesBO(@SerializedName("T1348647853363") val list: List<NewsDO> = listOf())
