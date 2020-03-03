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
package net.chris.news.fondue.android.extension

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso
import timber.log.Timber
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit.MILLISECONDS

object ImageLibraryHelper {

    private const val corePoolSize = 1

    private const val maximumPoolSize = 150

    private const val keepAliveTime = 60L

    private lateinit var picasso: Picasso

    fun getPicasso(context: Context): Picasso {
        if (ImageLibraryHelper::picasso.isInitialized) {
            return picasso
        }
        Timber.d("# Picasso has NOT been initialized.")
        picasso = Picasso.Builder(context).executor(
            ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                MILLISECONDS,
                LinkedBlockingQueue<Runnable>()
            )
        ).build()
        return picasso
    }
}

fun ImageView.load(
    context: Context,
    path: String?,
    @DrawableRes loadingPlaceholder: Int,
    @DrawableRes errorPlaceholder: Int
) {
    val picasso = ImageLibraryHelper.getPicasso(context)
    path?.isNotBlank()?.let { validPath ->
        when (validPath) {
            true -> picasso
                .load(path)
                .placeholder(loadingPlaceholder)
                .error(errorPlaceholder)
                .fit()
                .centerCrop()
                .into(this)
            else -> picasso.load(errorPlaceholder)
        }
    } ?: picasso.load(errorPlaceholder)
}

fun ImageView.loadAndResize(
    context: Context,
    path: String?,
    @DrawableRes loadingPlaceholder: Int,
    @DrawableRes errorPlaceholder: Int,
    width: Int
) {
    val picasso = ImageLibraryHelper.getPicasso(context)
    path?.isNotBlank()?.let { validPath ->
        when (validPath) {
            true -> picasso
                .load(path)
                .placeholder(loadingPlaceholder)
                .error(errorPlaceholder)
                .resize(width, 0)
                .into(this)
            else -> picasso.load(errorPlaceholder)
        }
    } ?: picasso.load(errorPlaceholder)
}
