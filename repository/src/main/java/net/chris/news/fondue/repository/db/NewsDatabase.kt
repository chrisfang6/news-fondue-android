package net.chris.news.fondue.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import net.chris.news.fondue.repository.dao.NewsDAO
import net.chris.news.fondue.repository.po.NewsPO

@Database(entities = [NewsPO::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDAO
}
