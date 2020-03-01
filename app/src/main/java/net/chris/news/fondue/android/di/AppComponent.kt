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
