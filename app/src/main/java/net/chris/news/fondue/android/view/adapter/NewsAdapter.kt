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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import net.chris.news.fondue.android.R
import net.chris.news.fondue.android.vo.NewsVO

class NewsAdapter : PagedListAdapter<NewsVO, NewsViewHolder>(ITEM_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_layout, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        } ?: holder.clear()
    }

    companion object {
        private val ITEM_DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsVO>() {
            override fun areItemsTheSame(oldItem: NewsVO, newItem: NewsVO): Boolean = oldItem.docId == newItem.docId

            override fun areContentsTheSame(oldItem: NewsVO, newItem: NewsVO): Boolean = oldItem == newItem
        }
    }
}
