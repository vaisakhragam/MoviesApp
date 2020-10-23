package com.example.moviesapplication.ui.main.viewmodel


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.BuildConfig
import com.example.moviesapplication.data.local.db.entities.Movies
import com.example.moviesapplication.data.repository.APIRepository
import com.example.moviesapplication.data.repository.DBRepository
import com.example.moviesapplication.utils.NetworkHelper
import com.example.moviesapplication.utils.Resource
import kotlinx.coroutines.launch

class MoviesViewModel @ViewModelInject constructor(
    private val APIRepository: APIRepository,
    private val networkHelper: NetworkHelper, val dBRepository: DBRepository
) : ViewModel() {

    var count=0
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



                    _movies.postValue(Resource.success(dBRepository.getMovies()))
                }


            } else {
                fetchMovies()
            }
        }

    }

    private fun fetchMovies() {
        viewModelScope.launch {
            _movies.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                APIRepository.getMovies(BuildConfig.APIKEY).let {
                    if (it.isSuccessful) {
                        println("data=${it.body()?.results}")

                        insertToDB(it.body()?.results)
                        _movies.postValue(Resource.success(it.body()?.results))


                    } else _movies.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _movies.postValue(Resource.error("No internet connection", null))
        }
    }


    fun insertToDB(movies:List<Movies>?) {


        viewModelScope.launch {

            movies?.map {
                    dBRepository.insertMoviesData(it)
                }

            }
        }
    }





