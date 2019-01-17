package com.onemonster.movienotes.model.entity

import com.squareup.moshi.Json

data class Movie(
    @get:Json(name = "vote_count") val voteCount: Int = 0,
    @get:Json(name = "id") val id: Int = 0,
    @get:Json(name = "video") val video: Boolean = false,
    @get:Json(name = "vote_average") val voteAverage: Double = 0.0,
    @get:Json(name = "title") val title: String = "",
    @get:Json(name = "popularity") val popularity: Double = 0.0,
    @get:Json(name = "poster_path") val posterPath: String = "",
    @get:Json(name = "original_language") val originalLanguage: String = "",
    @get:Json(name = "original_title") val originalTitle: String = "",
    @get:Json(name = "genre_ids") val genreIds: List<Int> = listOf(),
    @get:Json(name = "backdrop_path") val backdropPath: String = "",
    @get:Json(name = "adult") val adult: Boolean = false,
    @get:Json(name = "overview") val overview: String = "",
    @get:Json(name = "release_date") val releaseDate: String = ""
)