package net.chris.news.fondue.android.log

import android.util.Log
import timber.log.Timber

class CrashReportingTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.VERBOSE,
            Log.DEBUG -> return
            Log.WARN -> {
            }
            Log.ERROR -> {
            }
        }
    }
}
