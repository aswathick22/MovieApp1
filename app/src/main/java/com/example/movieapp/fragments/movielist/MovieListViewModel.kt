package com.example.movieapp.fragments.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.remote.api.MovieDBClient
import com.example.movieapp.remote.data.MovieResult
import com.example.movieapp.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val _movieList = MutableLiveData<MovieResult>()
    val movieList : MutableLiveData<MovieResult> get() = _movieList

    init{
        val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
        viewModelScope.launch {
            _movieList.value = repository.getMovieLists()
        }
    }
}