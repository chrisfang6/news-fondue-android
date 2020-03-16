/**
 *   Copyright 2019 chrisfang6
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package net.chris.news.fondue.repository.converter

import net.chris.news.fondue.domain.NewsDO
import net.chris.news.fondue.repository.po.NewsPO
import net.chris.news.fondue.usecase.NewsType.HEADLINES
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class NewsDO2POConverterImpl @Inject constructor() : NewsDO2POConverter {

    override fun apply(newsDO: NewsDO): NewsPO = NewsPO(
        newsDO.digest,
        newsDO.docid ?: "",
        false,
        newsDO.imgsrc,
        newsDO.ptime?.let { DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(it) } ?: DateTime(),
        newsDO.source,
        newsDO.title ?: "",
        newsDO.tname ?: HEADLINES.name,
        newsDO.url
    )
}
