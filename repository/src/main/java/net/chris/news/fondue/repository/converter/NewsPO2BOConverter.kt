package net.chris.news.fondue.repository.converter

import net.chris.news.fondue.repository.po.NewsPO
import net.chris.news.fondue.usecase.bo.NewsBO
import net.chris.news.fondue.usecase.converter.Converter

interface NewsPO2BOConverter : Converter<NewsPO, NewsBO>
