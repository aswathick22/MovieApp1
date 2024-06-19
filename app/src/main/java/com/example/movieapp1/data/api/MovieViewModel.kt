package com.example.movieapp1.data.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp1.data.movie.MovieId
import com.example.movieapp1.data.movie.MovieResult
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val _movieId = MutableLiveData<List<MovieId>>()
    val movieId : MutableLiveData<List<MovieId>> get() = _movieId
    private val _result = MutableLiveData<List<MovieResult>>()
    val result : MutableLiveData<List<MovieResult>> get() = _result

    init{
        viewModelScope.launch {
            getMovieId()
        }
        viewModelScope.launch{
            getResult()
        }
    }

    private suspend fun getMovieId(){
        _movieId.value = MovieDBClient.movieDBInterface.getMovieIds()
    }
    private suspend fun getResult(){
        _result.value = MovieDBClient.movieDBInterface.getResults()
    }
}