package com.example.movieapp.fragments.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.remote.api.MovieDBClient
import com.example.movieapp.remote.data.LatestMovieList
import com.example.movieapp.remote.data.MovieResult
import com.example.movieapp.remote.data.UpcomingMovieList
import com.example.movieapp.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {

    private val _movieList = MutableLiveData<MovieResult>()
    val movieList : MutableLiveData<MovieResult> get() = _movieList

    private val _topRatedMovieList = MutableLiveData<MovieResult>()
    val topRatedMovieList : MutableLiveData<MovieResult> get() = _topRatedMovieList

    private val _upcomingMovieList = MutableLiveData<MovieResult>()
    val upcomingMovieList : MutableLiveData<MovieResult> get() = _upcomingMovieList

    val filterClicked = MutableLiveData(0)

    private val _allSearchResults = MutableLiveData<MovieResult>()
    val allSearchResults : MutableLiveData<MovieResult> get() = _allSearchResults

    init{
        getAllMovieLists()
    }

    fun getAllMovieLists(){
        val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
        viewModelScope.launch {
            _movieList.value = repository.getMovieLists()
        }
    }

    fun getTopRatedMoviesList(){
        val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
        viewModelScope.launch {
            _topRatedMovieList.value = repository.getTopRatedMovieList()
        }
    }

    fun getUpcomingMoviesList(){
        val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
        viewModelScope.launch {
            _upcomingMovieList.value = repository.getUpcomingMovieList()
        }
    }

    fun getAllSearchResults(title : String){
        val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
        viewModelScope.launch {
            _allSearchResults.value = repository.getAllSearchResults(title)
        }
    }

}