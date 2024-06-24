package com.example.movieapp.repository

import com.example.movieapp.remote.api.MovieDBClient.API_KEY
import com.example.movieapp.remote.data.MovieDetails
import com.example.movieapp.remote.data.MovieResult
import com.example.movieapp.remote.data.PopularMovieDetails
import com.example.movieapp.remote.data.PopularMovieList
import retrofit2.http.GET

interface MovieRepository {

    @GET("movie/now_playing?api_key=$API_KEY&language=en-US")
    suspend fun getMovieLists() : MovieResult

    @GET("movie/{movie_id}?api_key=$API_KEY&language=en-US")
    suspend fun getMovieDetails() : MovieDetails

    @GET("movie/popular?api_key=$API_KEY&language=en-US")
    suspend fun getPopularMovieLists() : PopularMovieList

}




