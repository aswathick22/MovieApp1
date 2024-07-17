package com.example.movieapp.remote.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieVideos(
    @Json(name = "id")
    val id: Int,
    @Json(name = "results")
    val results: List<ResultXXX>
)