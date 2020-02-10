package net.chris.news.fondue.android

import com.facebook.stetho.Stetho
import timber.log.Timber.DebugTree

class NewsDebugApplication : NewsApplication() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
    }

    override fun generateTree() = DebugTree()
}
