package com.example.moviesapplication.data.local.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "movies_entity")

data class Movies(

        @PrimaryKey
        @SerializedName("id")
        @ColumnInfo(name = "id")
        val id: Int = 0,

        @SerializedName("popularity")
        @ColumnInfo(name = "popularity")
        val popularity: Float=0F,

        @SerializedName("vote_count")
        @ColumnInfo(name = "vote_count")
        val vote_count: Int = 0,

        @SerializedName("video")
        @ColumnInfo(name = "video")
        val video: String = "",

        @SerializedName("poster_path")
        @ColumnInfo(name = "poster_path")
        val poster_path: String = "",


        @SerializedName("adult")
        @ColumnInfo(name = "adult")
        val adult: String = "",

        @SerializedName("backdrop_path")
        @ColumnInfo(name = "backdrop_path")
        val backdrop_path: String = "",

        @SerializedName("original_language")
        @ColumnInfo(name = "original_language")
        val original_language: String = "",

        @SerializedName("original_title")
        @ColumnInfo(name = "original_title")
        val original_title: String = "",

        @SerializedName("title")
        @ColumnInfo(name = "title")
        val title: String = "",

        @SerializedName("vote_average")
        @ColumnInfo(name = "vote_average")
        val vote_average: Float = 0F,

        @SerializedName("overview")
        @ColumnInfo(name = "overview")
        val overview: String = "",

        @SerializedName("release_date")
        @ColumnInfo(name = "release_date")
        val release_date: String = "",

        @ColumnInfo(name = "is_favorite")
val is_favorite: Int= 0


   /*     @SerializedName("genre_ids")
        @Ignore
        val genre_ids: List<Integer>*/
)


data class ResultsHolder(


        @SerializedName("results")
        val results: List<Movies>
)

