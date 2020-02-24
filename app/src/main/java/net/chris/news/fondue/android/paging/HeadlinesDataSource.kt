package net.chris.news.fondue.android.paging

import androidx.paging.ItemKeyedDataSource
import net.chris.news.fondue.android.vo.NewsVO

/**
 * DataSource for Headlines.
 * Use String (the doc id of [NewsVO]) to query NewsVO.
 */
abstract class HeadlinesDataSource : ItemKeyedDataSource<String, NewsVO>()
