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
package net.chris.news.fondue.android.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import net.chris.news.fondue.android.converter.NewsBO2VOConverter
import net.chris.news.fondue.android.vo.NewsVO
import net.chris.news.fondue.usecase.NewsListUseCase
import javax.inject.Inject

class HeadlinesDataSourceFactoryImpl @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val newsListUseCase: NewsListUseCase,
    private val converter: NewsBO2VOConverter
) : HeadlinesDataSourceFactory() {

    private val _sourceLiveData = MutableLiveData<HeadlinesDataSource>()

    override fun create(): DataSource<String, NewsVO> {
        val dataSource = HeadlinesDataSourceImpl(compositeDisposable, newsListUseCase, converter)
        _sourceLiveData.postValue(dataSource)
        return dataSource
    }

    override fun currentDataSource(): DataSource<String, NewsVO>? = _sourceLiveData.value
}
