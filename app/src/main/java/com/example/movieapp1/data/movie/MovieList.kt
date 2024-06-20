package com.example.movieapp1.data.movie


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResult(
    @Json(name = "dates")
    val dates: Dates,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<MovieItem>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)