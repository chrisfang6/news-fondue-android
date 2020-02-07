package net.chris.news.fondue.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import net.chris.news.fondue.repository.Constant.COLUMN_POST_TIME
import net.chris.news.fondue.repository.Constant.COLUMN_TYPE
import net.chris.news.fondue.repository.Constant.TABLE_NAME_NEWS
import net.chris.news.fondue.repository.po.NewsPO

@Dao
interface NewsDAO {

    @Query("SELECT * FROM $TABLE_NAME_NEWS WHERE $COLUMN_TYPE=:type ORDER BY $COLUMN_POST_TIME DESC")
    fun getAllNews(type: String): List<NewsPO>

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg news: NewsPO)
}
