package net.chris.news.fondue.android.di

import android.content.Context
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import net.chris.news.fondue.android.BuildConfig
import net.chris.news.fondue.repository.NewsListRepositoryImpl
import net.chris.news.fondue.repository.converter.NewsConverter
import net.chris.news.fondue.repository.converter.NewsPersistentConverter
import net.chris.news.fondue.repository.dao.NewsDAO
import net.chris.news.fondue.repository.db.NewsDatabase
import net.chris.news.fondue.repository.network.NewsApi
import net.chris.news.fondue.usecase.repo.NewsListRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
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
    abstract fun bindNewsPersistentConverter(newsPersistentConverter: NewsPersistentConverter): NewsConverter

    companion object {

        @Singleton
        @Provides
        fun provideNewsApi(baseUrl: String): NewsApi {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) Level.BODY else Level.NONE))
                        .addNetworkInterceptor(StethoInterceptor())
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NewsApi::class.java)
        }

        @Singleton
        @Provides
        fun provideCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }

        @Singleton
        @Provides
        fun provideNewsDatabase(context: Context): NewsDatabase {
            return Room.databaseBuilder(
                context,
                NewsDatabase::class.java,
                "news_database"
            ).build()
        }

        @Singleton
        @Provides
        fun provideNewsDAO(newsDatabase: NewsDatabase): NewsDAO {
            return newsDatabase.newsDao()
        }
    }
}
