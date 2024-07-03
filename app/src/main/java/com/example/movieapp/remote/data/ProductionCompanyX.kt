package com.example.movieapp.remote.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompanyX(
    @Json(name = "id")
    val id: Int,
    @Json(name = "logo_path")
    val logoPath: Any,
    @Json(name = "name")
    val name: String,
    @Json(name = "origin_country")
    val originCountry: String
)