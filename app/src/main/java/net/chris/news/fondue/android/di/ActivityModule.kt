package net.chris.news.fondue.android.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.chris.news.fondue.android.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
