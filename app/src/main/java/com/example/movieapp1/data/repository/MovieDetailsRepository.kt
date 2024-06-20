package com.example.movieapp1.data.repository

import androidx.lifecycle.LiveData
import com.example.movieapp1.data.api.MovieDBInterface
import com.example.movieapp1.data.movie.MovieResult


class MovieRepository(private val apiService : MovieDBInterface) {

    suspend fun getMovieList() : MovieResult{
        return apiService.getMovieLists()
    }
}