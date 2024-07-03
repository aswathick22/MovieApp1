package com.example.movieapp.remote.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpcomingMovieList(
    @Json(name = "dates")
    val dates: DatesX,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<ResultX>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)