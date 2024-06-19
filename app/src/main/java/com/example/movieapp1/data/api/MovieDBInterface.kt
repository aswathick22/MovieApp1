package com.example.movieapp1.data.api

import com.example.movieapp1.data.movie.MovieId
import com.example.movieapp1.data.movie.MovieResult
import retrofit2.http.GET

interface MovieDBInterface {

    @GET("movie/{movie_id}")
    suspend fun getMovieIds():List<MovieId>

    @GET("movie/{movie_id}")
    suspend fun getResults():List<MovieResult>

}




