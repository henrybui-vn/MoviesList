package com.android.movieslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.movieslist.R
import com.android.movieslist.databinding.FragmentMovieDetailsBinding
import com.bumptech.glide.Glide

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        initUI(arguments?.getString(KEY_IMDB_ID))
        observeChanges()
        return binding.root
    }

    private fun initUI(imdbID: String?) {
        viewModel.getMovieDetails(checkNotNull(imdbID))
    }

    private fun observeChanges() {
        viewModel.movieDetails.observe(viewLifecycleOwner) {
            with(binding) {
                pbLoading.isVisible = false
                layoutDetails.isVisible = true
                Glide.with(requireContext()).load(it.poster).error(R.drawable.ic_broken_image)
                    .into(ivMoviePoster)
                tvMovieTitle.text = it.title
                tvMovieYear.text = it.year
                tvMovieCategory.text = it.genre
                tvMovieRuntime.text = it.runtime
                tvMovieIMDB.text = it.imdbRating
                tvMovieSynopsisContent.text = it.plot
                tvMovieScorePoint.text = it.imdbRating
                tvMovieReviewsPoint.text = it.metascore
                tvMoviePopularityPoint.text = it.imdbVotes
                tvDirectorName.text = it.director
                tvWriterName.text = it.writer
                tvActorsName.text = it.actors
            }
        }
    }

    companion object {
        const val KEY_IMDB_ID = "imdb_id"
    }
}