package net.chris.news.fondue.android.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.chris.news.fondue.android.viewmodel.NewsViewModel
import net.chris.news.fondue.android.viewmodel.NewsViewModelFactory
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Singleton
    @Binds
    abstract fun bindNewsViewModelFactory(factory: NewsViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel
}
