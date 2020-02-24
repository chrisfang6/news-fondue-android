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
