package com.onemonster.movienotes.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.onemonster.movienotes.R
import com.onemonster.movienotes.base.BaseAdapter
import com.onemonster.movienotes.base.BaseViewHolder
import com.onemonster.movienotes.model.entity.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : BaseAdapter<Movie, MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    class MovieViewHolder(view: View) : BaseViewHolder<Movie>(view) {
        override fun bind(data: Movie, position: Int) {
            view.text_title.text = data.title
        }
    }
}