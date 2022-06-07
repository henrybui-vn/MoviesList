package com.android.movieslist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.movieslist.R
import com.android.movieslist.databinding.FragmentMoviesListBinding
import com.android.movieslist.ui.MovieDetailsFragment.Companion.KEY_IMDB_ID
import com.android.movieslist.ui.adapter.MoviesListAdapter

class MoviesListFragment : Fragment() {
    private lateinit var binding: FragmentMoviesListBinding

    private lateinit var viewModel: MoviesListViewModel

    private lateinit var adapter: MoviesListAdapter

    private val navController: NavController
        get() = requireActivity().findNavController(R.id.fragmentContainerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MoviesListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        initUI()
        observeChanges()
        return binding.root
    }

    private fun initUI() {
        adapter = MoviesListAdapter {
            navController.navigate(
                R.id.action_moviesListFragment_to_movieDetailsFragment,
                bundleOf(KEY_IMDB_ID to it)
            )
        }
        val lm = GridLayoutManager(context, 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (adapter.itemCount % 2 == 0 || position < adapter.itemCount - 1) 1 else 2
                }
            }
        }
        binding.rvMoviesList.adapter = adapter
        binding.rvMoviesList.layoutManager = lm
        binding.etSearch.setOnEditorActionListener { textView, _, _ ->
            adapter.submitList(emptyList())
            binding.pbLoading.isVisible = true
            viewModel.getMoviesList(textView.text.toString())
            hideKeyboard()
            true
        }
    }

    private fun observeChanges() {
        viewModel.moviesList.observe(viewLifecycleOwner) {
            binding.pbLoading.isVisible = false
            adapter.submitList(it)
        }
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
    }
}