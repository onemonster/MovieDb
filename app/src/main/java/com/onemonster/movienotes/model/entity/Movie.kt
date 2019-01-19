package com.onemonster.movienotes.model.entity

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity(primaryKeys = ["id"])
data class Movie(
    // from api response
    @field:Json(name = "vote_count") val voteCount: Int = 0,
    @field:Json(name = "id") val id: Int = 0,
    @field:Json(name = "video") val video: Boolean = false,
    @field:Json(name = "vote_average") val voteAverage: Double = 0.0,
    @field:Json(name = "title") val title: String = "",
    @field:Json(name = "popularity") val popularity: Double = 0.0,
    @field:Json(name = "poster_path") val posterPath: String = "",
    @field:Json(name = "original_language") val originalLanguage: String = "",
    @field:Json(name = "original_title") val originalTitle: String = "",
    @field:Json(name = "genre_ids") val genreIds: List<Int> = listOf(),
    @field:Json(name = "backdrop_path") val backdropPath: String = "",
    @field:Json(name = "adult") val adult: Boolean = false,
    @field:Json(name = "overview") val overview: String = "",
    @field:Json(name = "release_date") val releaseDate: String = "",
    // custom fields
    val page: Int,
    val note: String
)