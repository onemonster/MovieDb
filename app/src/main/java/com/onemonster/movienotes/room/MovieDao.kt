package com.onemonster.movienotes.room

import androidx.room.*
import com.onemonster.movienotes.model.entity.Movie
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<Movie>)

    @Update
    fun update(movie: Movie): Completable

    @Update
    fun updateAll(movies: List<Movie>): Single<Int>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getMovieById(id: Int): Single<Movie>

    @Query("SELECT * FROM Movie WHERE page = :page")
    fun getMovieList(page: Int): Single<List<Movie>>
}