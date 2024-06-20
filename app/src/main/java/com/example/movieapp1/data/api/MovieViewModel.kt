package com.example.movieapp1.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp1.data.movie.MovieDetails
import com.example.movieapp1.data.movie.MovieResult
import com.example.movieapp1.data.repository.MovieRepository
import com.example.movieapp1.data.repository.NetworkState
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val _movieList = MutableLiveData<MovieResult>()
    val movieList : MutableLiveData<MovieResult> get() = _movieList

    private val _movieId = MutableLiveData<LiveData<MovieDetails>>()
    val movieId : MutableLiveData<LiveData<MovieDetails>> get() = _movieId

    private val _networkState = MutableLiveData<LiveData<NetworkState>>()
    val networkState : MutableLiveData<LiveData<NetworkState>> get() = _networkState

    init{
        val repository = MovieRepository(MovieDBClient.movieDBInterface)
        viewModelScope.launch {
            _movieList.value = repository.getMovieList()
        }
    }

    private suspend fun getMovieDetail(){
        _movieId.value = MovieDBClient.movieDBInterface.getMovieDetails()
    }
    /*private suspend fun getNetworkState(){
        _networkState.value = MovieDBClient.movieDBInterface.getNetworkStates()
    }*/
   /* private suspend fun getResult(){
        _result.value = MovieDBClient.movieDBInterface.getResults()
    }*/
}