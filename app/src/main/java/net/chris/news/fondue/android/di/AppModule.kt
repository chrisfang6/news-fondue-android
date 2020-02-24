package net.chris.news.fondue.android.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import net.chris.news.fondue.android.converter.NewsBO2VOConverter
import net.chris.news.fondue.android.converter.NewsBO2VOConverterImpl
import net.chris.news.fondue.android.paging.HeadlinesDataSource
import net.chris.news.fondue.android.paging.HeadlinesDataSourceFactory
import net.chris.news.fondue.android.paging.HeadlinesDataSourceFactoryImpl
import net.chris.news.fondue.android.paging.HeadlinesDataSourceImpl
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
    abstract fun bindHeadlinesDataSource(dataSource: HeadlinesDataSourceImpl): HeadlinesDataSource

    @Singleton
    @Binds
    abstract fun bindHeadlinesDataSourceFactory(factory: HeadlinesDataSourceFactoryImpl): HeadlinesDataSourceFactory

    companion object {

        @Singleton
        @Provides
        fun provideCompositeDisposable() = CompositeDisposable()
    }
}
