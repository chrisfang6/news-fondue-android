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
package net.chris.news.fondue.web

import io.reactivex.Single
import net.chris.news.fondue.repository.network.NewsWebService
import net.chris.news.fondue.repository.po.HeadlinesPO
import javax.inject.Inject

class NewsWebServiceImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsWebService {

    override fun fetchHeadlines(category: String, startIndex: Int): Single<HeadlinesPO> = newsApi.fetchHeadlines(category, startIndex)
}
