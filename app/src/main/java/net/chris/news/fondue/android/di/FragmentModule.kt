package net.chris.news.fondue.android.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.chris.news.fondue.android.view.DetailFragment
import net.chris.news.fondue.android.view.MainFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment
}
