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

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso

fun ImageView.load(
    path: String?,
    @DrawableRes loadingPlaceholder: Int,
    @DrawableRes errorPlaceholder: Int
) {
    path?.isNotBlank()?.let { validPath ->
        when (validPath) {
            true -> Picasso.get().load(path).placeholder(loadingPlaceholder).error(errorPlaceholder).into(this)
            else -> setImageResource(errorPlaceholder)
        }
    } ?: setImageResource(errorPlaceholder)
}
