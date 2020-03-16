/**
 *   Copyright 2019 chrisfang6
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package net.chris.news.fondue.database

import net.chris.news.fondue.database.dao.NewsDAO
import net.chris.news.fondue.repository.database.NewsDatabaseHandler
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
