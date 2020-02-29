package net.chris.news.fondue.repository.converter

import net.chris.news.fondue.repository.po.NewsPO
import net.chris.news.fondue.usecase.bo.NewsBO
import javax.inject.Inject

class NewsPO2BOConverterImpl @Inject constructor() : NewsPO2BOConverter {

    override fun apply(newsPO: NewsPO): NewsBO = NewsBO(
        newsPO.digest,
        newsPO.docId,
        newsPO.favorite,
        newsPO.imageSrc,
        newsPO.postTime,
        newsPO.source,
        newsPO.title,
        newsPO.type,
        newsPO.url
    )
}
