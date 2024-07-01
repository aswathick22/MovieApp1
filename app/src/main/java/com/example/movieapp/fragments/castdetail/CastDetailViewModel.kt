package com.example.movieapp.fragments.castdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.remote.api.MovieDBClient
import com.example.movieapp.remote.data.MovieCastDetails
import com.example.movieapp.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch

class CastDetailViewModel : ViewModel() {

    private val personIdLiveData = MutableLiveData(0)

    private val _castDetail = MutableLiveData<MovieCastDetails>()
    val castDetail : MutableLiveData<MovieCastDetails> get() = _castDetail

    fun updateMovieId(personId : Int){
        personIdLiveData.value = personId
        val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
        viewModelScope.launch {
            _castDetail.value = repository.getCastDetails(personIdLiveData.value?:0)
        }
    }

}