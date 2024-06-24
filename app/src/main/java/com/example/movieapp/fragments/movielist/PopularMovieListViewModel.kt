package com.example.movieapp.fragments.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.remote.api.MovieDBClient
import com.example.movieapp.remote.data.PopularMovieList
import com.example.movieapp.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch

class PopularMovieListViewModel : ViewModel() {
    private val _popularMovieList = MutableLiveData<PopularMovieList>()
    val popularMovieList : MutableLiveData<PopularMovieList> get() = _popularMovieList

    init{
        val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
        viewModelScope.launch {
            _popularMovieList.value = repository.getPopularMovieLists()
        }
    }
}