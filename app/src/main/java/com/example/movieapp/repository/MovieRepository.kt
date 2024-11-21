package com.example.movieapp.repository

import com.example.movieapp.remote.api.MovieDBClient.API_KEY
import com.example.movieapp.remote.data.AddedList
import com.example.movieapp.remote.data.Buy
import com.example.movieapp.remote.data.MovieCastDetails
import com.example.movieapp.remote.data.MovieCastList
import com.example.movieapp.remote.data.MovieDetails
import com.example.movieapp.remote.data.MovieResult
import com.example.movieapp.remote.data.MovieReview
import com.example.movieapp.remote.data.MovieVideos
import com.example.movieapp.remote.data.PopularMovieList
import com.example.movieapp.remote.data.Rent
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRepository {

    @GET("movie/now_playing?api_key=$API_KEY&language=en-US")
    suspend fun getMovieLists() : MovieResult

    @GET("movie/{movie_id}?api_key=$API_KEY&language=en-US")
    suspend fun getMovieDetails(@Path("movie_id") movieId : Int) : MovieDetails

    @GET("movie/popular?api_key=$API_KEY&language=en-US")
    suspend fun getPopularMovieLists() : PopularMovieList

    @GET("movie/{movie_id}/credits?api_key=$API_KEY&language=en-US")
    suspend fun getCastList(@Path("movie_id") movieId : Int) : MovieCastList

    @GET("person/{person_id}?api_key=$API_KEY&language=en-US")
    suspend fun getCastDetails(@Path("person_id") personId : Int) : MovieCastDetails

    @GET("movie/top_rated?api_key=$API_KEY&language=en-US")
    suspend fun getTopRatedMovieList() : MovieResult

    @GET("movie/upcoming?api_key=$API_KEY&language=en-US")
    suspend fun getUpcomingMovieList() : MovieResult

    @GET("search/movie?api_key=$API_KEY&language=en-US")
    suspend fun getAllSearchResults(@Query("query") title : String) : MovieResult

    @GET("search/movie?api_key=$API_KEY&language=en-US")
    suspend fun getPopularSearchResults(@Query("query") title : String) : PopularMovieList

    @GET("movie/{movie_id}/reviews?api_key=$API_KEY&language=en-US")
    suspend fun getMovieReviews(@Path("movie_id") movieId : Int) : MovieReview

    @GET("movie/{movie_id}/videos?api_key=$API_KEY&language=en-US")
    suspend fun getMovieTrailer(@Path("movie_id") movieId : Int) : MovieVideos

    @GET("movie/{movie_id}/lists?api_key=$API_KEY&language=en-US")
    suspend fun getAddedList(@Path("movie_id") movieId: Int) : AddedList

    @GET("movie/{movie_id}/watch/providers?api_key=$API_KEY&language=en-US")
    suspend fun buyMovie(@Path("movie_id") movieId: Int) : Buy

    @GET("movie/{movie_id}/watch/providers?api_key=$API_KEY&language=en-US")
    suspend fun rentMovie(@Path("movie_id") movieId: Int) : Rent

}




