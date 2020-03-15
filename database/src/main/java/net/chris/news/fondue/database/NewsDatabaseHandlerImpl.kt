package net.chris.news.fondue.database

import net.chris.news.fondue.database.dao.NewsDAO
import net.chris.news.fondue.repository.NewsDatabaseHandler
import net.chris.news.fondue.repository.po.NewsPO
import javax.inject.Inject

class NewsDatabaseHandlerImpl @Inject constructor(
    private val newsDAO: NewsDAO
) : NewsDatabaseHandler {

    override fun getAllNews(type: String, limitedNumber: Int): List<NewsPO>? =
        newsDAO.getAllNews(type, limitedNumber)

    override fun getAllNewsAfter(type: String, afterDocId: String, limitedNumber: Int): List<NewsPO>? =
        newsDAO.getAllNewsAfter(type, afterDocId, limitedNumber)

    override fun getAllNewsBefore(type: String, beforeDocId: String, limitedNumber: Int): List<NewsPO>? =
        newsDAO.getAllNewsBefore(type, beforeDocId, limitedNumber)

    override fun insertAll(news: Array<out NewsPO>) =
        newsDAO.insertAll(*news)
}
