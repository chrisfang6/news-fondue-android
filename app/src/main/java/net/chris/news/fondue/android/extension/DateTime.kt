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

import org.joda.time.DateTime
import org.joda.time.Duration
import org.joda.time.format.DateTimeFormat

fun DateTime.toPresentString(): String {
    val formatter = DateTimeFormat.forPattern("yyyy年MM月dd日 HH:mm:ss")
    val hourFormatter = DateTimeFormat.forPattern("HH:mm:ss")
    val currentTime = DateTime()
    val duration = Duration(this, currentTime)
    return when {
        duration.standardMinutes < 0 -> "刚刚"
        duration.standardMinutes < 60 -> "${duration.standardMinutes}分钟前"
        duration.standardHours < 24 && isInSameDay(currentTime) -> "${duration.standardHours}小时前"
        isYesterdayOf(currentTime) -> "昨天${hourFormatter.print(this)}"
        isTheDayBeforeYesterdayOf(currentTime) -> "前天${hourFormatter.print(this)}"
        else -> formatter.print(this)
    }
}

fun DateTime.isInSameDay(current: DateTime): Boolean = (year == current.year) && (current.dayOfYear == dayOfYear)

fun DateTime.isYesterdayOf(current: DateTime): Boolean = plusDays(1).isInSameDay(current)

fun DateTime.isTheDayBeforeYesterdayOf(current: DateTime): Boolean = plusDays(2).isInSameDay(current)
