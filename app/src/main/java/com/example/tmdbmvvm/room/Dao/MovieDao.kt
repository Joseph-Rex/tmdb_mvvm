package com.example.roomsample


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tmdbmvvm.MovieTable
import androidx.lifecycle.LiveData




@Dao
interface MovieDao {

    @get:Query("SELECT * FROM movietable")
    val all: List<MovieTable>

    @Query("SELECT * FROM movietable WHERE movietable.`indexvalue` = :indexValue")
    fun getMoviesBy(indexValue : Int): List<MovieTable>

    /*@Query("SELECT * FROM movie WHERE title LIKE :title AND " + "last_name LIKE :releaseDate LIMIT 1")
    fun findByName(title: String, releaseDate: String): MovieTable*/

    @Insert
    fun insertAll(movies: List<MovieTable>)

    @Delete
    fun delete(client: MovieTable)

    /*@Query("SELECT COUNT() FROM movietable")
    fun getNumberOfRows(): Int*/

    /*@Query("SELECT COUNT(id) FROM movietable")
    fun getNumberOfRows(): Int*/

    @Query("SELECT COUNT(*) FROM movietable")
    fun getNumberOfRows(): LiveData<Int>

}
