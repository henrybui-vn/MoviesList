package com.android.movieslist.net.model

import com.android.movieslist.model.Movie
import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("Search")
    val moviesList: List<Movie>,
    @SerializedName("totalResults")
    val totalResults: String,
    @SerializedName("Response")
    val response: String
)