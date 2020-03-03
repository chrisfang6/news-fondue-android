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
package net.chris.news.fondue.android.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_news_layout.view.*
import net.chris.news.fondue.android.R
import net.chris.news.fondue.android.extension.load
import net.chris.news.fondue.android.extension.toPresentString
import net.chris.news.fondue.android.vo.NewsVO

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(news: NewsVO) {
        itemView.news_title.text = news.title
        itemView.news_img.load(itemView.context, news.imageSrc, R.drawable.ic_news_loading, R.drawable.ic_news_error)
        itemView.news_source.text = news.source
        itemView.news_timestamp.text = news.postTime.toPresentString()
    }

    /**
     * Clear and use the placeholder item.
     */
    fun clear() {
    }
}
