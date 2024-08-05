package com.example.movieapp.remote.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MX(
    @Json(name = "flatrate")
    val flatrate: List<Flatrate>,
    @Json(name = "link")
    val link: String
)