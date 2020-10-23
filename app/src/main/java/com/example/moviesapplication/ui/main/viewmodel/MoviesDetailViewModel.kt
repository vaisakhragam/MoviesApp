package com.example.moviesapplication.ui.main.viewmodel


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.data.local.db.entities.Movies
import com.example.moviesapplication.data.repository.APIRepository
import com.example.moviesapplication.data.repository.DBRepository
import com.example.moviesapplication.utils.NetworkHelper
import com.example.moviesapplication.utils.Resource
import kotlinx.coroutines.launch

class MoviesDetailViewModel @ViewModelInject constructor(
    private val APIRepository: APIRepository,
    private val networkHelper: NetworkHelper, val dBRepository: DBRepository
) : ViewModel() {

    var count = 0
    private val _movie = MutableLiveData<Resource<Movies>>()
    private val _isFav = MutableLiveData<Boolean>()
    private var MOVIE_ID = ""
    private var isFavorite = false;
    val movie: LiveData<Resource<Movies>>
        get() = _movie
    val isfav: LiveData<Boolean>
        get() = _isFav










    private fun fetchMovies(id: String) {
        viewModelScope.launch {



            _movie.postValue(Resource.success(dBRepository.getMovieByID(id)))
        }
    }

    fun setMovieID(id: String) {
        MOVIE_ID = id

        viewModelScope.launch {
            val id = dBRepository.getFavoriteID(MOVIE_ID)
            if(id==1)
            {
                isFavorite=true

            }
            else
            {
                isFavorite=false
            }

            _isFav.postValue(isFavorite)
        }
        fetchMovies(id)
    }

    fun onFavoriteClicked() {




        if (!isFavorite) {
            //repository.favoriteMovie(movieDetails.getMovie())
            viewModelScope.launch {
                dBRepository.addFavoriteMovie(MOVIE_ID)
            }


            isFavorite = true
            //  showSnackbarMessage(R.string.movie_added_successfully)
        } else {

            isFavorite = false
            viewModelScope.launch {
                dBRepository.removeFavoriteMovie(MOVIE_ID)
            }
            //showSnackbarMessage(R.string.movie_removed_successfully)
        }
    }

    fun isFavorite() = isFavorite
}







