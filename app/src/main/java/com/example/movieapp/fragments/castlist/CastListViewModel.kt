package com.example.movieapp.fragments.castlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.remote.api.MovieDBClient
import com.example.movieapp.remote.data.MovieCastList
import com.example.movieapp.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch

class CastListViewModel : ViewModel() {
    private val movieIdLiveData = MutableLiveData(0)
    private val _castList = MutableLiveData<MovieCastList>()
    val castList : MutableLiveData<MovieCastList> get() = _castList

    /*init{
        val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
        viewModelScope.launch {
            _castList.value = repository.getCastList()
        }
    }*/

    fun updateMovieId(movieId : Int){
        movieIdLiveData.value = movieId
        val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
        viewModelScope.launch {
            _castList.value = repository.getCastList(movieIdLiveData.value?:0)
        }
    }
}