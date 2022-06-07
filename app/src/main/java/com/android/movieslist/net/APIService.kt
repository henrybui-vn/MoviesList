package com.android.movieslist.net

import com.android.movieslist.net.model.MovieDetailsResponse
import com.android.movieslist.net.model.MoviesListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {
    @GET("/")
    fun getMoviesList(@Query("apiKey") apiKey: String, @Query("s") search: String, @Query("type") type: String): Observable<MoviesListResponse>

    @GET("/")
    fun getMovieDetails(@Query("apiKey") apiKey: String, @Query("i") i: String): Observable<MovieDetailsResponse>
}