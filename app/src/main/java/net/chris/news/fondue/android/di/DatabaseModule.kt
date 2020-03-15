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
package net.chris.news.fondue.android.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import net.chris.news.fondue.database.NewsDatabaseHandlerImpl
import net.chris.news.fondue.database.dao.NewsDAO
import net.chris.news.fondue.database.db.NewsDatabase
import net.chris.news.fondue.repository.Constant.DATABASE_NAME
import net.chris.news.fondue.repository.NewsDatabaseHandler
import javax.inject.Singleton

@Module
abstract class DatabaseModule {

    @Singleton
    @Binds
    abstract fun bindNewsDatabaseHandler(handler: NewsDatabaseHandlerImpl): NewsDatabaseHandler

    companion object {

        @Singleton
        @Provides
        fun provideNewsDatabase(context: Context): NewsDatabase {
            return Room.databaseBuilder(
                context,
                NewsDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

        @Singleton
        @Provides
        fun provideNewsDAO(newsDatabase: NewsDatabase): NewsDAO {
            return newsDatabase.newsDao()
        }
    }
}
