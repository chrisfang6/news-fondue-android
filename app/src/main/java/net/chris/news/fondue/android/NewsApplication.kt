package net.chris.news.fondue.android

import android.app.Application
import net.chris.news.fondue.android.log.CrashReportingTree
import timber.log.Timber
import timber.log.Timber.DebugTree

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(if (BuildConfig.DEBUG) DebugTree() else CrashReportingTree())
    }
}
