package com.android.movieslist.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.movieslist.net.APIService
import com.android.movieslist.net.model.MovieDetailsResponse
import com.android.movieslist.utils.API_KEY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.java.KoinJavaComponent

class MovieDetailsViewModel : ViewModel() {
    private val apiService: APIService by KoinJavaComponent.inject(APIService::class.java)

    private val _movieDetails = MutableLiveData<MovieDetailsResponse>()
    val movieDetails: LiveData<MovieDetailsResponse> = _movieDetails

    @SuppressLint("CheckResult")
    fun getMovieDetails(imdbID: String) {
        apiService.getMovieDetails(API_KEY, imdbID).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    handleResponse(result)
                },
                { error ->
                    println(error.message)
                }
            )
    }

    private fun handleResponse(movieDetails: MovieDetailsResponse) {
        _movieDetails.value = movieDetails
    }
}