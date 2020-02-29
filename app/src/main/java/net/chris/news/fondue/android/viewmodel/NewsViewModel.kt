package net.chris.news.fondue.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import net.chris.news.fondue.android.paging.HeadlinesDataSourceFactory
import net.chris.news.fondue.android.paging.PagingConstant.PAGING_SIZE
import net.chris.news.fondue.android.vo.NewsVO
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    factory: HeadlinesDataSourceFactory
) : BaseViewModel() {

    val headlinesLiveData: LiveData<PagedList<NewsVO>> = LivePagedListBuilder(factory, PAGING_SIZE).build()
}
