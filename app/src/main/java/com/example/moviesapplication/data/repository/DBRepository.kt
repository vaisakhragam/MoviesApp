package com.example.moviesapplication.data.repository


import com.example.moviesapplication.data.local.db.dao.MoviesDAO
import com.example.moviesapplication.data.local.db.entities.Movies
import javax.inject.Inject


class DBRepository @Inject constructor(private val moviesDAO: MoviesDAO) {

    suspend fun insertMoviesData(movies: Movies) = moviesDAO.insertMovies(movies)
    suspend fun getMovies() = moviesDAO.getMovies()
    suspend fun getMoviesCount() = moviesDAO.getMoviesCount()
    suspend  fun addFavoriteMovie(movieID: String) = moviesDAO.addFavoriteMovie(movieID)
    suspend  fun removeFavoriteMovie(movieID: String) = moviesDAO.removeFavoriteMovie(movieID)
    suspend  fun getFavoriteID(movieID: String) = moviesDAO.getFavoriteID(movieID)
    suspend  fun getFavoriteMovies() = moviesDAO.getFavoriteMovies()
    suspend  fun getMovieByID(movieID: String) = moviesDAO.getMovieById(movieID)
}