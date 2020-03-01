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
package net.chris.news.fondue.domain

data class NewsDO(
    val alias: String?,
    val boardid: String?,
    val cid: String?,
    val commentStatus: Int = 0,
    val daynum: String?,
    val digest: String?,
    val docid: String?,
    val ename: String?,
    val hasAD: Int = 0,
    val hasCover: Boolean = false,
    val hasHead: Int = 0,
    val hasIcon: Boolean = false,
    val hasImg: Int = 0,
    val imgsrc: String?,
    val lmodify: String?,
    val ltitle: String?,
    val mtime: String?,
    val order: Int = 0,
    val postid: String?,
    val priority: Int = 0,
    val ptime: String?,
    val quality: Int = 0,
    val replyCount: Int = 0,
    val source: String?,
    val sourceId: String?,
    val subtitle: String?,
    val template: String?,
    val title: String?,
    val tname: String?,
    val topic_background: String?,
    val url: String?,
    val url_3w: String?,
    val votecount: Int = 0
)
