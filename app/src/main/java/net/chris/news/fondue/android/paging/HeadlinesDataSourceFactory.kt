package net.chris.news.fondue.android.paging

import androidx.paging.DataSource
import net.chris.news.fondue.android.vo.NewsVO

abstract class HeadlinesDataSourceFactory : DataSource.Factory<String, NewsVO>()
