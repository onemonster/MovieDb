package com.onemonster.movienotes.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

class RecyclerViewPaginationScrollListener(
    private val layoutManager: RecyclerView.LayoutManager,
    private val threshold: Int = 0,
    private val isLoading: () -> Boolean,
    private val onLast: () -> Boolean = { true },
    private val loadMore: (Int) -> Unit
) : RecyclerView.OnScrollListener() {

    var currentPage: Int = 0

    init {
        Timber.d("Initialize Scroll Listener")
        loadMore(++currentPage)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = when (layoutManager) {
            is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
            is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
            else -> return
        }

        Timber.d("Scroll Listener $visibleItemCount $totalItemCount $firstVisibleItemPosition")

        if (onLast() || isLoading()) return

        if ((visibleItemCount + firstVisibleItemPosition + threshold) >= totalItemCount) {
            loadMore(++currentPage)
        }
    }
}
