package net.chris.news.fondue.android.di

import android.content.Context
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import net.chris.news.fondue.android.BuildConfig
import net.chris.news.fondue.repository.Constant.DATABASE_NAME
import net.chris.news.fondue.repository.NewsListRepositoryImpl
import net.chris.news.fondue.repository.converter.NewsPO2BOConverter
import net.chris.news.fondue.repository.converter.NewsPO2BOConverterImpl
import net.chris.news.fondue.repository.converter.NewsPersistentConverter
import net.chris.news.fondue.repository.converter.NewsPersistentConverterImpl
import net.chris.news.fondue.repository.dao.NewsDAO
import net.chris.news.fondue.repository.db.NewsDatabase
import net.chris.news.fondue.repository.network.NewsApi
import net.chris.news.fondue.usecase.repo.NewsListRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindNewsListRepository(repository: NewsListRepositoryImpl): NewsListRepository

    @Singleton
    @Binds
    abstract fun bindNewsPersistentConverter(converter: NewsPersistentConverterImpl): NewsPersistentConverter

    @Singleton
    @Binds
    abstract fun bindNewsPO2BOConverter(converter: NewsPO2BOConverterImpl): NewsPO2BOConverter

    companion object {

        @Singleton
        @Provides
        fun provideNewsApi(baseUrl: String): NewsApi {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) BODY else NONE))
                        .addInterceptor(StethoInterceptor())
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NewsApi::class.java)
        }

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
