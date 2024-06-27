package com.example.movieapp.repository

import com.example.movieapp.remote.api.MovieDBClient.API_KEY
import com.example.movieapp.remote.data.Cast
import com.example.movieapp.remote.data.MovieCastList
import com.example.movieapp.remote.data.MovieDetails
import com.example.movieapp.remote.data.MovieResult
import com.example.movieapp.remote.data.PopularMovieList
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieRepository {

    @GET("movie/now_playing?api_key=$API_KEY&language=en-US")
    suspend fun getMovieLists() : MovieResult

    @GET("movie/{movie_id}?api_key=$API_KEY&language=en-US")
    suspend fun getMovieDetails(@Path("movie_id") movieId : Int) : MovieDetails

    @GET("movie/popular?api_key=$API_KEY&language=en-US")
    suspend fun getPopularMovieLists() : PopularMovieList

    @GET("movie/{movie_id}/credits?api_key=$API_KEY&language=en-US")
    suspend fun getCastList() : MovieCastList
}




