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
package net.chris.news.fondue.repository

object Constant {

    internal const val MAX_TRY = 10

    const val SIZE_PER_REQUEST = 20

    const val DATABASE_NAME = "news_database"

    const val TABLE_NAME_NEWS = "news"

    const val COLUMN_POST_TIME = "post_time"

    const val COLUMN_TYPE = "type"

    const val COLUMN_DOC_ID = "doc_id"
}
