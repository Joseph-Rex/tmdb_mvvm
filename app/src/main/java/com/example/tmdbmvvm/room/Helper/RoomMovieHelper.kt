package com.example.tmdbmvvm.room.Helper

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.tmdbmvvm.AppDatabase
import com.example.tmdbmvvm.MovieTable
import com.example.tmdbmvvm.data.domains.Movie
import com.example.tmdbmvvm.preference.PreferenceHelper
import kotlin.Exception

class RoomMovieHelper(private val ctx: Context) {

    fun insertMovies(it: List<Movie>) {
        Thread {

            var preferenceHelper: PreferenceHelper = PreferenceHelper(ctx)
            var db = Room.databaseBuilder(ctx, AppDatabase::class.java, "MovieDB").build()
            val movies: MutableList<MovieTable> = mutableListOf()
            var indexValue = preferenceHelper.getOfflineCount()
            Log.i("ffff before", "initial insert :" + indexValue.toString())

            for (index: Int in 0..19) {
                var item: Movie = it.get(index)
                var posterPath: String
                try {
                    posterPath = item.posterPath
                    if (posterPath.equals(null) || posterPath.trim().equals("")) {
                        posterPath = "/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg"
                    } else {
                        posterPath = item.posterPath
                    }

                } catch (e: Exception) {
                    posterPath = "/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg"
                }

                val movie =
                    MovieTable( indexValue, item.id, posterPath, item.releaseDate, item.title, item.overview, item.original_language, item.vote_count, item.vote_average, item.adult)
                movies.add(index, movie)
            }
            db.movieDao().insertAll(movies)
            preferenceHelper.setOfflineCount(preferenceHelper.getOfflineCount() + 1)


            val movies2: List<MovieTable> = db.movieDao().all

            for (index: Int in 0..19) {
                var movie: MovieTable = movies2.get(index)
                Log.i(
                    "ffff title :" + movie.title,
                    " releaseDate:" + movie.releaseDate + " indexValue" + movie.indexvalue
                )
            }

        }.start()
    }


    fun getMovieListByIndexValue(indexValue: Int): List<Movie> {
        var db = Room.databaseBuilder(ctx, AppDatabase::class.java, "MovieDB").build()

        var moviesTables: List<MovieTable> = db.movieDao().getMoviesBy(indexValue)
        var movies: MutableList<Movie> = mutableListOf()

        for (index: Int in 0..19) {
            var mt = moviesTables.get(index)
            var movie = Movie(mt.id, mt.posterPath.toString(), mt.releaseDate.toString(), mt.title.toString(), mt.overview.toString(), mt.original_language.toString(), mt.vote_count!!, mt.vote_average!!, mt.adult)
            movies.add(movie)
        }

        return movies

    }

}