package com.example.moviesapplication.data.local.db.dao

import androidx.room.*
import com.example.moviesapplication.data.local.db.entities.Movies

@Dao
interface MoviesDAO {


    @Query("SELECT * FROM movies_entity")
    suspend fun getMovies(): List<Movies>

    @Query("SELECT count(*) FROM movies_entity")
    suspend fun getMoviesCount():Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: Movies)

    @Delete
    suspend fun delete(movie: Movies)

    @Query("UPDATE movies_entity SET is_favorite = 1 WHERE id = :movieId")
   suspend  fun addFavoriteMovie(movieId: String): Int


    @Query("UPDATE movies_entity SET is_favorite = 0 WHERE id = :movieId")
   suspend  fun removeFavoriteMovie(movieId: String): Int

    @Query("SELECT is_favorite FROM movies_entity WHERE id = :movieId")
    suspend  fun getFavoriteID(movieId: String): Int

    @Query("SELECT * FROM movies_entity WHERE is_favorite = 1")
    suspend  fun getFavoriteMovies(): List<Movies>

    @Query("SELECT * FROM movies_entity WHERE id = :movieId")
    suspend  fun getMovieById(movieId: String): Movies
}