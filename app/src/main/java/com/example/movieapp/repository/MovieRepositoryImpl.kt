package com.example.movieapp.repository

import com.example.movieapp.remote.data.Cast
import com.example.movieapp.remote.data.MovieCastList
import com.example.movieapp.remote.data.MovieDetails
import com.example.movieapp.remote.data.MovieResult
import com.example.movieapp.remote.data.PopularMovieDetails
import com.example.movieapp.remote.data.PopularMovieList


open class MovieRepositoryImpl(private val apiService : MovieRepository) : MovieRepository{

    override suspend fun getMovieLists(): MovieResult {
        return apiService.getMovieLists()
    }

    override suspend fun getMovieDetails(movieId : Int): MovieDetails {
        return apiService.getMovieDetails(movieId)
    }

    override suspend fun getPopularMovieLists(): PopularMovieList {
        return apiService.getPopularMovieLists()
    }

    override suspend fun getCastList(movieId: Int): MovieCastList {
        return apiService.getCastList(movieId)
    }

}