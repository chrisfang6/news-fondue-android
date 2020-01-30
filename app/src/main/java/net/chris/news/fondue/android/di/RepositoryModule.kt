package net.chris.news.fondue.android.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import net.chris.news.fondue.repository.NewsApi
import net.chris.news.fondue.repository.NewsListRepositoryImpl
import net.chris.news.fondue.usecase.NewsListRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsListRepository(repository: NewsListRepositoryImpl): NewsListRepository

    companion object {

        @Provides
        fun provideNewsApi(baseUrl: String): NewsApi {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(NewsApi::class.java)
        }

        @Provides
        fun provideCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }
    }
}
