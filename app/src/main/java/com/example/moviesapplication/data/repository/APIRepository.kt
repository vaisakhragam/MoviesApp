package com.example.moviesapplication.data.repository


import com.example.moviesapplication.data.remote.api.ApiHelper
import javax.inject.Inject

class APIRepository @Inject constructor(private val apiHelper: ApiHelper) {


    suspend fun getMovies(apiKey:String) =  apiHelper.getAllMovies(apiKey)





}