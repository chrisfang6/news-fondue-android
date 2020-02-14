package net.chris.news.fondue.android

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.chris.news.fondue.android.di.DaggerAppComponent
import net.chris.news.fondue.android.log.CrashReportingTree
import timber.log.Timber.Tree
import timber.log.Timber.plant
import javax.inject.Inject

open class NewsApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        inject()

        plant(generateTree())
    }

    fun inject() = generateAppComponent().inject(this)

    open fun generateTree(): Tree = CrashReportingTree()

    fun generateAppComponent() = DaggerAppComponent.builder().baseUrl("http://c.m.163.com/").applicationContext(this).build()

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}
