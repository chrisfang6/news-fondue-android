package net.chris.news.fondue.android

import android.app.Application
import net.chris.news.fondue.android.di.AppComponent
import net.chris.news.fondue.android.di.DaggerAppComponent
import net.chris.news.fondue.android.log.CrashReportingTree
import timber.log.Timber.Tree
import timber.log.Timber.plant

open class NewsApplication : Application() {

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = generateAppComponent()

        plant(generateTree())
    }

    open fun generateTree(): Tree = CrashReportingTree()

    private fun generateAppComponent() = DaggerAppComponent.builder().baseUrl("http://c.m.163.com/").applicationContext(this).build()

    fun getAppComponent() = component
}
