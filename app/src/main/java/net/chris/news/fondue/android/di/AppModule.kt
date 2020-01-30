package net.chris.news.fondue.android.di

import dagger.Binds
import dagger.Module
import net.chris.news.fondue.usecase.NewsListUseCase
import net.chris.news.fondue.usecase.NewsListUseCaseImpl

@Module
abstract class AppModule {

    @Binds
    abstract fun bindNewsListUseCase(useCase: NewsListUseCaseImpl): NewsListUseCase
}
