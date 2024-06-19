package com.example.movieapp1.data.movie


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieId(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val movieResults: List<MovieResult>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)