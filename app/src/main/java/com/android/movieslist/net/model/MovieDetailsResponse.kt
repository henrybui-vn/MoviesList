package com.android.movieslist.net.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("Rated")
    val rated: String,
    @SerializedName("Released")
    val released: String,
    @SerializedName("Runtime")
    val runtime: String,
    @SerializedName("Genre")
    val genre: String,
    @SerializedName("Director")
    val director: String,
    @SerializedName("Writer")
    val writer: String,
    @SerializedName("Actors")
    val actors: String,
    @SerializedName("Plot")
    val plot: String,
    @SerializedName("Language")
    val language: String,
    @SerializedName("Country")
    val country: String,
    @SerializedName("Awards")
    val awards: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("imdbRating")
    val imdbRating: String,
    @SerializedName("Metascore")
    val metascore: String,
    @SerializedName("imdbVotes")
    val imdbVotes: String,
    @SerializedName("Response")
    val response: String
)