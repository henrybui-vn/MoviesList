package com.android.movieslist.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.movieslist.R
import com.android.movieslist.databinding.CellMovieBinding
import com.android.movieslist.model.Movie
import com.bumptech.glide.Glide

class MoviesListAdapter(private val onMovieClick: (String) -> Unit) : ListAdapter<Movie, MoviesListAdapter.MyTradeMeMessagesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTradeMeMessagesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CellMovieBinding.inflate(inflater, parent, false)
        return MyTradeMeMessagesViewHolder(onMovieClick, binding, parent.context)
    }

    override fun onBindViewHolder(holder: MyTradeMeMessagesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class MyTradeMeMessagesViewHolder(
        private val onMovieClick: (String) -> Unit,
        private val binding: CellMovieBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) = with(binding) {
            tvMovieTitle.text = movie.title
            tvMovieTitle.isSelected = true
            Glide.with(context).load(movie.poster).error(R.drawable.ic_broken_image).into(ivMoviePoster)
            binding.root.setOnClickListener {
                onMovieClick(movie.imdbID)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}