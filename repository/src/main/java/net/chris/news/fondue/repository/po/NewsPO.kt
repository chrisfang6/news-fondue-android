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
package net.chris.news.fondue.repository.po

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import net.chris.news.fondue.repository.Constant.COLUMN_DOC_ID
import net.chris.news.fondue.repository.Constant.COLUMN_POST_TIME
import net.chris.news.fondue.repository.Constant.COLUMN_TYPE
import net.chris.news.fondue.repository.Constant.TABLE_NAME_NEWS
import net.chris.news.fondue.repository.converter.DateConverter
import org.joda.time.DateTime

@Entity(tableName = TABLE_NAME_NEWS)
@TypeConverters(DateConverter::class)
data class NewsPO(
    val digest: String?,
    @PrimaryKey @ColumnInfo(name = COLUMN_DOC_ID) val docId: String,
    val favorite: Boolean = false,
    @ColumnInfo(name = "photo") val imageSrc: String?,
    @ColumnInfo(name = COLUMN_POST_TIME) val postTime: DateTime,
    val source: String?,
    val title: String,
    @ColumnInfo(name = COLUMN_TYPE) val type: String,
    val url: String?
)
