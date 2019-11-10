package com.example.tmdbmvvm.data.api

import com.example.tmdbmvvm.data.domains.MovieDetails
import com.example.tmdbmvvm.data.domains.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    @GET("movie/now_playing")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>
}