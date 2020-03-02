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

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import net.chris.news.fondue.android.converter.NewsBO2VOConverter
import net.chris.news.fondue.android.converter.NewsBO2VOConverterImpl
import net.chris.news.fondue.android.paging.HeadlinesDataSourceFactory
import net.chris.news.fondue.android.paging.HeadlinesDataSourceFactoryImpl
import net.chris.news.fondue.usecase.NewsListUseCase
import net.chris.news.fondue.usecase.NewsListUseCaseImpl
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindNewsListUseCase(useCase: NewsListUseCaseImpl): NewsListUseCase

    @Singleton
    @Binds
    abstract fun bindNewsBO2VOConverter(converter: NewsBO2VOConverterImpl): NewsBO2VOConverter

    @Singleton
    @Binds
    abstract fun bindHeadlinesDataSourceFactory(factory: HeadlinesDataSourceFactoryImpl): HeadlinesDataSourceFactory

    companion object {

        @Singleton
        @Provides
        fun provideCompositeDisposable() = CompositeDisposable()
    }
}
