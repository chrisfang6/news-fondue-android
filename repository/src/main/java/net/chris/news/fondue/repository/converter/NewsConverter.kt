package net.chris.news.fondue.repository.converter

import net.chris.news.fondue.domain.NewsDO
import net.chris.news.fondue.repository.po.NewsPO
import net.chris.news.fondue.usecase.NewsType

interface NewsConverter : Converter<NewsDO, NewsType, NewsPO>
