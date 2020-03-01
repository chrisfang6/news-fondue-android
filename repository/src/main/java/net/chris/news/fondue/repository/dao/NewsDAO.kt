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
package net.chris.news.fondue.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import net.chris.news.fondue.repository.Constant.COLUMN_DOC_ID
import net.chris.news.fondue.repository.Constant.COLUMN_POST_TIME
import net.chris.news.fondue.repository.Constant.COLUMN_TYPE
import net.chris.news.fondue.repository.Constant.TABLE_NAME_NEWS
import net.chris.news.fondue.repository.po.NewsPO

@Dao
interface NewsDAO {

    @Query("SELECT * FROM $TABLE_NAME_NEWS WHERE $COLUMN_TYPE=:type ORDER BY $COLUMN_POST_TIME DESC LIMIT :limitedNumber")
    fun getAllNews(type: String, limitedNumber: Int): List<NewsPO>?

    @Query("SELECT * FROM $TABLE_NAME_NEWS WHERE $COLUMN_TYPE=:type AND $COLUMN_POST_TIME < (SELECT $COLUMN_POST_TIME FROM $TABLE_NAME_NEWS WHERE $COLUMN_DOC_ID=:afterDocId) ORDER BY $COLUMN_POST_TIME DESC LIMIT :limitedNumber")
    fun getAllNewsAfter(type: String, afterDocId: String, limitedNumber: Int): List<NewsPO>?

    @Query("SELECT * from $TABLE_NAME_NEWS WHERE $COLUMN_TYPE=:type AND $COLUMN_POST_TIME > (SELECT $COLUMN_POST_TIME FROM $TABLE_NAME_NEWS WHERE $COLUMN_DOC_ID=:beforeDocId) ORDER BY $COLUMN_POST_TIME LIMIT :limitedNumber")
    fun getAllNewsBefore(type: String, beforeDocId: String, limitedNumber: Int): List<NewsPO>?

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg news: NewsPO)
}
