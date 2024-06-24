package com.example.movieapp.fragments.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.remote.api.MovieDBClient
import com.example.movieapp.remote.data.MovieDetails
import com.example.movieapp.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {

    /*private val _movieDetail = MutableLiveData<MovieDetails>()
    val movieDetail : MutableLiveData<MovieDetails> get() = _movieDetail

    init{
        val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
        viewModelScope.launch {
            _movieDetail.value = repository.getPopularMovieLists()
        }
    }*/

}