package net.chris.news.fondue.repository

import net.chris.news.fondue.repository.po.NewsPO

interface NewsDatabaseHandler {

    fun getAllNews(type: String, limitedNumber: Int): List<NewsPO>?

    fun getAllNewsAfter(type: String, afterDocId: String, limitedNumber: Int): List<NewsPO>?

    fun getAllNewsBefore(type: String, beforeDocId: String, limitedNumber: Int): List<NewsPO>?

    fun insertAll(vararg news: NewsPO)
}
