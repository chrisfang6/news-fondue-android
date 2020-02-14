package net.chris.news.fondue.android.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import net.chris.news.fondue.android.NewsApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityModule::class
    ]
)
interface AppComponent {

    fun inject(application: NewsApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun baseUrl(baseUrl: String): Builder

        @BindsInstance
        fun applicationContext(context: Context): Builder

        fun build(): AppComponent
    }
}
