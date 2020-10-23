package com.example.moviesapplication.data.remote.api


import com.example.moviesapplication.data.local.db.entities.ResultsHolder

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("now_playing")
    suspend fun getMovies(
                                @Query("api_key") apiKey: String,): Response<ResultsHolder>




}