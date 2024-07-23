package com.example.movieapp.addedlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.remote.api.MovieDBClient
import com.example.movieapp.remote.data.AddedList
import com.example.movieapp.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch

class AddedListViewModel : ViewModel() {

    private val movieIdLiveData = MutableLiveData(0)

    private val _addedList = MutableLiveData<AddedList>()

    val addedList : MutableLiveData<AddedList> get() = _addedList

    fun getAddedLists(movieId : Int){
        movieIdLiveData.value = movieId
        val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
        viewModelScope.launch {
            _addedList.value = repository.getAddedList(movieId)
        }
    }
}