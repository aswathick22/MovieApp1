package com.example.movieapp.repository

import com.example.movieapp.remote.data.AddedList
import com.example.movieapp.remote.data.MovieCastDetails
import com.example.movieapp.remote.data.MovieCastList
import com.example.movieapp.remote.data.MovieDetails
import com.example.movieapp.remote.data.MovieResult
import com.example.movieapp.remote.data.MovieReview
import com.example.movieapp.remote.data.MovieVideos
import com.example.movieapp.remote.data.PopularMovieList


open class MovieRepositoryImpl(private val apiService: MovieRepository/*, private val movieDao: MovieDao*/) : MovieRepository{

    override suspend fun getMovieLists(): MovieResult {
        return apiService.getMovieLists()
    }

    override suspend fun getMovieDetails(movieId : Int): MovieDetails {
        return apiService.getMovieDetails(movieId)
    }

    override suspend fun getPopularMovieLists(): PopularMovieList {
        return apiService.getPopularMovieLists()
    }

    override suspend fun getCastList(movieId : Int): MovieCastList {
        return apiService.getCastList(movieId)
    }

    override suspend fun getCastDetails(personId : Int): MovieCastDetails {
        return apiService.getCastDetails(personId)
    }

    override suspend fun getTopRatedMovieList(): MovieResult {
        return apiService.getTopRatedMovieList()
    }

    override suspend fun getUpcomingMovieList(): MovieResult {
        return apiService.getUpcomingMovieList()
    }

    override suspend fun getAllSearchResults(title: String): MovieResult {
        return apiService.getAllSearchResults(title)
    }

    override suspend fun getPopularSearchResults(title: String): PopularMovieList {
        return apiService.getPopularSearchResults(title)
    }

    override suspend fun getMovieReviews(movieId: Int): MovieReview {
        return apiService.getMovieReviews(movieId)
    }

    override suspend fun getMovieTrailer(movieId: Int): MovieVideos {
        return apiService.getMovieTrailer(movieId)
    }

    override suspend fun getAddedList(movieId: Int): AddedList {
        return apiService.getAddedList(movieId)
    }

 /*   suspend fun insert(movie: UserMovieList) {
        movieDao.insert(movie)
    }

    suspend fun getMoviesForUser(userId: String): List<UserMovieList> {
        return movieDao.getMoviesForUser(userId)
    }

    suspend fun deleteMovieForUser(movieId: Int, userId: String) {
        movieDao.deleteMovieForUser(movieId, userId)
    }*/

}