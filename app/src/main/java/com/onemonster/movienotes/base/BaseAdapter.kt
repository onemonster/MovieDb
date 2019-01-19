package com.onemonster.movienotes.base

import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

abstract class BaseAdapter<T, VH : BaseViewHolder<T>>() : RecyclerView.Adapter<VH>() {
    protected var items: List<T> = listOf()

    fun add(item: T) {
        this.items = items.toMutableList().plus(item)
        notifyDataSetChanged()
    }

    fun add(items: List<T>) {
        Timber.d("Adding items: $items")
        this.items = this.items.toMutableList().plus(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position], position)
}