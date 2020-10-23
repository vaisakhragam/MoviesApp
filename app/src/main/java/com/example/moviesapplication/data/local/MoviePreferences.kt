package com.example.moviesapplication.data.local

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePreferences @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_MOVIE_ID = "PREF_KEY_MOVIE_ID"

    }

    fun getMovieId(): String? =
        prefs.getString(KEY_MOVIE_ID, null)

    fun setMovieId(userId: String) =
        prefs.edit().putString(KEY_MOVIE_ID, userId).apply()

    fun removeMovieId() =
        prefs.edit().remove(KEY_MOVIE_ID).apply()


}