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
import net.chris.news.fondue.repository.NewsListRepositoryImpl
import net.chris.news.fondue.repository.converter.NewsDO2POConverter
import net.chris.news.fondue.repository.converter.NewsDO2POConverterImpl
import net.chris.news.fondue.repository.converter.NewsPO2BOConverter
import net.chris.news.fondue.repository.converter.NewsPO2BOConverterImpl
import net.chris.news.fondue.usecase.repo.NewsListRepository
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
}
