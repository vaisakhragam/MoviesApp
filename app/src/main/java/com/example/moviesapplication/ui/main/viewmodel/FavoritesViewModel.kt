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

class FavoritesViewModel @ViewModelInject constructor(
    private val APIRepository: APIRepository,
    private val networkHelper: NetworkHelper, val dBRepository: DBRepository
) : ViewModel() {

    var count = 0
    private val _movies = MutableLiveData<Resource<List<Movies>>>()
    val movies: LiveData<Resource<List<Movies>>>
        get() = _movies

    init {


        count = 0
        viewModelScope.launch {
            count = dBRepository.getMoviesCount()
            println("DB count$count")
            if (count > 1) {


                println("after DB count$count")
                viewModelScope.launch {


                    _movies.postValue(Resource.success(dBRepository.getFavoriteMovies()))

                }


            }
        }

    }


    fun getFavorites()
    {
        viewModelScope.launch {


            _movies.postValue(Resource.success(dBRepository.getFavoriteMovies()))

        }
    }

}





