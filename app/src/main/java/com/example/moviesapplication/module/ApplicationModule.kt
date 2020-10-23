package com.example.moviesapplication.module


import android.content.Context
import android.content.SharedPreferences
import com.example.moviesapplication.BuildConfig
import com.example.moviesapplication.data.local.db.MoviesDB
import com.example.moviesapplication.data.local.db.dao.MoviesDAO
import com.example.moviesapplication.data.remote.api.ApiHelper
import com.example.moviesapplication.data.remote.api.ApiHelperImpl
import com.example.moviesapplication.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
    } else OkHttpClient
            .Builder()
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(
            okHttpClient: OkHttpClient,
            BASE_URL: String
    ): Retrofit =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper


    @Provides
    @Singleton
    fun provideMoviesDB(@ApplicationContext context: Context): MoviesDB =
            MoviesDB.getInstance(context)

    @Provides
    fun provideMoviesDAO(moviesDB:MoviesDB): MoviesDAO {
        return moviesDB.moviesDAO
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("movies-prefs", Context.MODE_PRIVATE)
}