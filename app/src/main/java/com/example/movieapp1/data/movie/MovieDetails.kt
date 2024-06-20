package com.example.movieapp1.data.movie


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetails(
    @Json(name = "budget")
    val budget: Int,
    @Json(name = "homepage")
    val homepage: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "imdb_id")
    val imdbId: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "revenue")
    val revenue: Int,
    @Json(name = "runtime")
    val runtime: Int,
    @Json(name = "status")
    val status: String,
    @Json(name = "tagline")
    val tagline: String,
    @Json(name = "title")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
)