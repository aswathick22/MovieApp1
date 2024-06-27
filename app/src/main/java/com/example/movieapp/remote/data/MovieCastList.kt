package com.example.movieapp.remote.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieCastList(
    @Json(name = "cast")
    val cast: List<Cast>,
    @Json(name = "crew")
    val crew: List<Crew>,
    @Json(name = "id")
    val id: Int
)