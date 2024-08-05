package com.example.movieapp.remote.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GH(
    @Json(name = "buy")
    val buy: List<BuyXXXXXXXXXXXXXXXXXXXXXX>,
    @Json(name = "link")
    val link: String,
    @Json(name = "rent")
    val rent: List<RentXXXXXXXXXXXXXXXXXXXXX>
)