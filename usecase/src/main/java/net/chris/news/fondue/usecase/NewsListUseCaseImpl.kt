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
package net.chris.news.fondue.usecase

import io.reactivex.Single
import net.chris.news.fondue.usecase.NewsType.HEADLINES
import net.chris.news.fondue.usecase.bo.NewsBO
import net.chris.news.fondue.usecase.repo.NewsListRepository
import javax.inject.Inject

class NewsListUseCaseImpl @Inject constructor(
    private val newsListRepository: NewsListRepository
) : NewsListUseCase {

    override fun getTopHeadlines(
        requestedLoadSize: Int
    ): Single<List<NewsBO>> = newsListRepository.getNewsList("T1348647853363", HEADLINES, requestedLoadSize)

    override fun getMoreHeadlines(
        afterId: String,
        requestedLoadSize: Int
    ): Single<List<NewsBO>> = newsListRepository.getNewsListAfter("T1348647853363", afterId, HEADLINES, requestedLoadSize)

    override fun getBeforeHeadlines(
        beforeId: String,
        requestedLoadSize: Int
    ): Single<List<NewsBO>> = newsListRepository.getNewsListBefore("T1348647853363", beforeId, HEADLINES, requestedLoadSize)
}
