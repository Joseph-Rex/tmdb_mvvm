package com.example.tmdbmvvm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class MovieTable constructor(indexvalue: Int, id: Int, posterPath: String, releaseDate: String, title: String, overview: String, original_language:String, vote_count:Int, vote_average:Float, adult:Boolean) {


    @PrimaryKey(autoGenerate = true)
    var index : Int = 0


    @ColumnInfo(name = "indexvalue")
    var indexvalue: Int = indexvalue

    @ColumnInfo(name = "id")
    var id: Int = id

    @ColumnInfo(name = "posterPath")
    var posterPath: String? = posterPath

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String? = releaseDate

    @ColumnInfo(name = "title")
    var title: String? = title


    @ColumnInfo(name = "overview")
    val overview: String? = overview

    @ColumnInfo(name = "original_language")
    val original_language: String? = original_language

    @ColumnInfo(name = "vote_count")
    val vote_count: Int? = vote_count

    @ColumnInfo(name = "vote_average")
    val vote_average: Float? = vote_average

    @ColumnInfo(name = "adult")
    val adult: Boolean = adult
}
