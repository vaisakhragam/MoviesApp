package com.example.moviesapplication.data.remote.api


import com.example.moviesapplication.data.local.db.entities.ResultsHolder
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getAllMovies(apiKey: String): Response<ResultsHolder> =apiService.getMovies(apiKey)




}