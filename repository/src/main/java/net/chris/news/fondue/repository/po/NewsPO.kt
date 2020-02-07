package net.chris.news.fondue.repository.po

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import net.chris.news.fondue.repository.Constant.COLUMN_POST_TIME
import net.chris.news.fondue.repository.Constant.COLUMN_TYPE
import net.chris.news.fondue.repository.Constant.TABLE_NAME_NEWS

@Entity(tableName = TABLE_NAME_NEWS)
data class NewsPO(
    val digest: String?,
    @PrimaryKey @ColumnInfo(name = "doc_id") val docId: String,
    val favorite: Boolean = false,
    @ColumnInfo(name = "photo") val imageSrc: String?,
    @ColumnInfo(name = COLUMN_POST_TIME) val postTime: String,
    val source: String?,
    val title: String,
    @ColumnInfo(name = COLUMN_TYPE) val type: String,
    val url: String?
)
