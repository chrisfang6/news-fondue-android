package net.chris.news.fondue.android.di

import dagger.BindsInstance
import dagger.Component
import net.chris.news.fondue.android.MainActivity

@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun baseUrl(baseUrl: String): Builder

        fun build(): AppComponent
    }
}