package com.example.movieapp.remote.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LV(
    @Json(name = "buy")
    val buy: List<BuyXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX>,
    @Json(name = "flatrate")
    val flatrate: List<Flatrate>,
    @Json(name = "link")
    val link: String,
    @Json(name = "rent")
    val rent: List<RentXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX>
)