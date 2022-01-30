package com.android.memeapp.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class InfiniteScrollListener : RecyclerView.OnScrollListener() {
    companion object {
        const val SCROLL_THRESOLD = 2
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (!hasMoreItem() || isLoading()) return
        val layout = recyclerView.layoutManager as? StaggeredGridLayoutManager ?: return
        val lastPositions = layout.findLastVisibleItemPositions(null)
        val lastVisiblePosition = lastPositions.maxOf { it }
        val itemCount = layout.itemCount

        if (lastVisiblePosition != RecyclerView.NO_POSITION &&
            lastVisiblePosition + 1 + SCROLL_THRESOLD >= itemCount
            && itemCount > 0
        ) {
            onLoadMore()
        }
    }

    abstract fun hasMoreItem(): Boolean
    abstract fun isLoading(): Boolean
    abstract fun onLoadMore()
}