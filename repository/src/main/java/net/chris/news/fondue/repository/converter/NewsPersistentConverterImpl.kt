package net.chris.news.fondue.repository.converter

import net.chris.news.fondue.domain.NewsDO
import net.chris.news.fondue.repository.po.NewsPO
import net.chris.news.fondue.usecase.NewsType
import net.chris.news.fondue.usecase.NewsType.HEADLINES
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class NewsPersistentConverterImpl @Inject constructor() : NewsPersistentConverter {

    override fun apply(newsDO: NewsDO): NewsPO = apply(newsDO, HEADLINES)

    fun apply(newsDO: NewsDO, newsType: NewsType): NewsPO = NewsPO(
        newsDO.digest,
        newsDO.docid ?: "",
        false,
        newsDO.imgsrc,
        newsDO.ptime?.let { DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(it) } ?: DateTime(),
        newsDO.source,
        newsDO.title ?: "",
        newsType.name,
        newsDO.url
    )
}
