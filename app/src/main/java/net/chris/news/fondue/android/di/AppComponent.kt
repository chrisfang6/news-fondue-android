package net.chris.news.fondue.android.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import net.chris.news.fondue.android.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun baseUrl(baseUrl: String): Builder

        @BindsInstance
        fun applicationContext(context: Context): Builder

        fun build(): AppComponent
    }
}
