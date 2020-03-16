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

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import net.chris.news.fondue.android.BuildConfig
import net.chris.news.fondue.repository.NewsListRepositoryImpl
import net.chris.news.fondue.repository.converter.NewsPO2BOConverter
import net.chris.news.fondue.repository.converter.NewsPO2BOConverterImpl
import net.chris.news.fondue.repository.converter.NewsDO2POConverter
import net.chris.news.fondue.repository.converter.NewsDO2POConverterImpl
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
    abstract fun bindNewsPersistentConverter(converter: NewsDO2POConverterImpl): NewsDO2POConverter

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
    }
}
