package com.example.movieapp.remote.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultXX(
    @Json(name = "author")
    val author: String,
    @Json(name = "author_details")
    val authorDetails: AuthorDetails,
    @Json(name = "content")
    val content: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "url")
    val url: String
)