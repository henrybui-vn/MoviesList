package com.android.movieslist.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.movieslist.model.Movie
import com.android.movieslist.net.APIService
import com.android.movieslist.utils.API_KEY
import com.android.movieslist.utils.TYPE_MOVIE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.java.KoinJavaComponent

class MoviesListViewModel : ViewModel() {
    private val apiService: APIService by KoinJavaComponent.inject(APIService::class.java)

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>> = _moviesList

    @SuppressLint("CheckResult")
    fun getMoviesList(searchKey: String) {
        apiService.getMoviesList(API_KEY, searchKey, TYPE_MOVIE).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    handleResponse(result.moviesList)
                },
                { error ->
                    println(error.message)
                }
            )
    }

    private fun handleResponse(moviesList: List<Movie>) {
        this._moviesList.value = moviesList
    }
}