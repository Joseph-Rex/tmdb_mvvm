package com.example.tmdbmvvm.data.domains


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    val id: Int,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    val title: String,

    val overview: String,

    val original_language: String,

    val vote_count: Int,

    val vote_average: Float,

    val adult: Boolean


):Serializable