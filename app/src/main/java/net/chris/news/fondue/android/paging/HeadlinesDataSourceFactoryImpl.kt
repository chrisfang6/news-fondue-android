package net.chris.news.fondue.android.paging

import androidx.paging.DataSource
import net.chris.news.fondue.android.vo.NewsVO
import javax.inject.Inject

class HeadlinesDataSourceFactoryImpl @Inject constructor(
    private val dataSource: HeadlinesDataSource
) : HeadlinesDataSourceFactory() {

    override fun create(): DataSource<String, NewsVO> = dataSource
}
