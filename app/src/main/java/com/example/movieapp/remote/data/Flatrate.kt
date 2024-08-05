package com.example.movieapp.remote.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Flatrate(
    @Json(name = "display_priority")
    val displayPriority: Int,
    @Json(name = "logo_path")
    val logoPath: String,
    @Json(name = "provider_id")
    val providerId: Int,
    @Json(name = "provider_name")
    val providerName: String
)