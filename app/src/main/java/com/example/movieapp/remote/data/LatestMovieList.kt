package com.example.movieapp.remote.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LatestMovieList(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: Any,
    @Json(name = "belongs_to_collection")
    val belongsToCollection: Any,
    @Json(name = "budget")
    val budget: Int,
    @Json(name = "genres")
    val genres: List<Any>,
    @Json(name = "homepage")
    val homepage: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "imdb_id")
    val imdbId: String,
    @Json(name = "origin_country")
    val originCountry: List<String>,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Int,
    @Json(name = "poster_path")
    val posterPath: Any,
    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompanyX>,
    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountryX>,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "revenue")
    val revenue: Int,
    @Json(name = "runtime")
    val runtime: Int,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguageX>,
    @Json(name = "status")
    val status: String,
    @Json(name = "tagline")
    val tagline: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Int,
    @Json(name = "vote_count")
    val voteCount: Int
)