package net.chris.news.fondue.repository.converter

import net.chris.news.fondue.domain.NewsDO
import net.chris.news.fondue.repository.po.NewsPO
import net.chris.news.fondue.usecase.NewsType
import javax.inject.Inject

class NewsPersistentConverter @Inject constructor() : NewsConverter {

    override fun apply(newsDO: NewsDO, newsType: NewsType): NewsPO = NewsPO(
        newsDO.digest,
        newsDO.docid ?: "",
        false,
        newsDO.imgsrc,
        newsDO.ptime ?: "",
        newsDO.source,
        newsDO.title ?: "",
        newsType.name,
        newsDO.url
    )
}
