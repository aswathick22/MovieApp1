package com.example.movieapp1.data.api

import androidx.lifecycle.LiveData
import com.example.movieapp1.data.api.MovieDBClient.API_KEY
import com.example.movieapp1.data.movie.MovieDetails
import com.example.movieapp1.data.movie.MovieResult
import retrofit2.http.GET

interface MovieDBInterface {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(): LiveData<MovieDetails>

    @GET("movie/now_playing?api_key=$API_KEY&language=en-US")
    suspend fun getMovieLists() : MovieResult


/*
    @GET("movie/{movie_id}")
    suspend fun getResults():List<MovieResult>
*/

}




