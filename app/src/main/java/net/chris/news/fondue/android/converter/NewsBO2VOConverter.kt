package net.chris.news.fondue.android.converter

import net.chris.news.fondue.android.vo.NewsVO
import net.chris.news.fondue.usecase.bo.NewsBO
import net.chris.news.fondue.usecase.converter.Converter

interface NewsBO2VOConverter : Converter<NewsBO, NewsVO>
