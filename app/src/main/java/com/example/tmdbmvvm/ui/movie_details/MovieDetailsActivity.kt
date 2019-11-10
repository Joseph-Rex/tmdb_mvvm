package com.example.tmdbmvvm.ui.movie_details


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.tmdbmvvm.R
import com.example.tmdbmvvm.data.api.POSTER_BASE_URL
import com.example.tmdbmvvm.data.domains.Movie
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailsActivity : AppCompatActivity() {

    private  lateinit var movie : Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movie = (intent.getSerializableExtra("movie") as? Movie)!!
        bindUI()

    }



    fun bindUI(){
        movie_title.text = movie.title
        movie_release_date.text = movie.releaseDate
        movie_rating.text = movie.vote_average.toString()
        total_votes.text = movie.vote_count.toString()
        movie_overview.text = movie.overview

        language.text = movie.original_language
        adult_movie.text = movie.adult.toString()

        val moviePosterURL = POSTER_BASE_URL + movie.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster);

    }
}

