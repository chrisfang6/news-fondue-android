package net.chris.news.fondue.android.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news_layout.view.*
import net.chris.news.fondue.android.R
import net.chris.news.fondue.android.vo.NewsVO

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(news: NewsVO) {
        itemView.news_title.text = news.title
        Picasso.get().load(news.imageSrc).placeholder(R.drawable.img_loading).error(R.drawable.img_error).into(itemView.news_img)
        itemView.news_source.text = news.source
        itemView.news_timestamp.text = news.postTime.toString()
    }

    /**
     * Clear and use the placeholder item.
     */
    fun clear() {
    }
}
