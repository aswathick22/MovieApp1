package com.example.movieapp.repository

import com.example.movieapp.remote.data.MovieDetails
import com.example.movieapp.remote.data.MovieResult


open class MovieRepositoryImpl(private val apiService : MovieRepository) : MovieRepository{

    override suspend fun getMovieLists(): MovieResult {
        return apiService.getMovieLists()
    }

    override suspend fun getMovieDetail(): MovieDetails {
        return apiService.getMovieDetail()
    }

}