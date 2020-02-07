package net.chris.news.fondue.android

import android.app.Application
import net.chris.news.fondue.android.di.AppComponent
import net.chris.news.fondue.android.di.DaggerAppComponent
import net.chris.news.fondue.android.log.CrashReportingTree
import timber.log.Timber
import timber.log.Timber.DebugTree

class NewsApplication : Application() {

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder().baseUrl("http://c.m.163.com/").applicationContext(this).build()

        Timber.plant(if (BuildConfig.DEBUG) DebugTree() else CrashReportingTree())
    }

    fun getAppComponent() = component
}
