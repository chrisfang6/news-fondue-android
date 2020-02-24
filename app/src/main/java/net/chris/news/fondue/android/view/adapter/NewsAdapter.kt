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
