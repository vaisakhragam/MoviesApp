package com.example.moviesapplication.data.remote.api

import com.example.moviesapplication.data.local.db.entities.ResultsHolder

import retrofit2.Response

interface ApiHelper {



    suspend fun getAllMovies(apiKey:String): Response<ResultsHolder>

}