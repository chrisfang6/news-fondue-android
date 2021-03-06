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
package net.chris.news.fondue.android.converter

import net.chris.news.fondue.android.vo.NewsVO
import net.chris.news.fondue.usecase.bo.NewsBO
import javax.inject.Inject

class NewsBO2VOConverterImpl @Inject constructor() : NewsBO2VOConverter {

    override fun apply(newsBO: NewsBO): NewsVO = NewsVO(
        newsBO.digest,
        newsBO.docId,
        newsBO.favorite,
        newsBO.imageSrc,
        newsBO.postTime,
        newsBO.source,
        newsBO.title,
        newsBO.type,
        newsBO.url
    )
}
