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
package net.chris.news.fondue.android

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import net.chris.news.fondue.android.di.DaggerAppComponent
import net.chris.news.fondue.android.log.CrashReportingTree
import timber.log.Timber.Tree
import timber.log.Timber.plant
import javax.inject.Inject

open class NewsApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        inject()

        plant(generateTree())
    }

    fun inject() = generateAppComponent().inject(this)

    open fun generateTree(): Tree = CrashReportingTree()

    fun generateAppComponent() = DaggerAppComponent.builder().baseUrl("http://c.m.163.com/").applicationContext(this).build()

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
