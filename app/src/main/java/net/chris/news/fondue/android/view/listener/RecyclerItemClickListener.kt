package net.chris.news.fondue.android.view.listener

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION

class RecyclerItemClickListener(
    context: Context,
    recyclerView: RecyclerView,
    listener: OnItemClickListener
) : RecyclerView.SimpleOnItemTouchListener() {

    private val gestureDetector: GestureDetector = GestureDetector(
        context,
        object : GestureDetector.SimpleOnGestureListener() {

            override fun onSingleTapUp(e: MotionEvent): Boolean =
                recyclerView.findChildViewUnder(e.x, e.y)?.let { child ->
                    val position = recyclerView.getChildAdapterPosition(child)
                    if (position != NO_POSITION) {
                        listener.onItemClick(position)
                        true
                    } else {
                        super.onSingleTapUp(e)
                    }
                } ?: super.onSingleTapUp(e)
        }
    )

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean = gestureDetector.onTouchEvent(e)

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
