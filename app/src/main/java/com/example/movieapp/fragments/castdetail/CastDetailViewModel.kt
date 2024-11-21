package com.example.movieapp.fragments.castdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.database.roomdatabase.data.MovieCastDao
import com.example.movieapp.database.roomdatabase.data.UserListDao
import com.example.movieapp.remote.api.MovieDBClient
import com.example.movieapp.remote.data.MovieCastDetails
import com.example.movieapp.remote.data.MovieItem
import com.example.movieapp.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch

class CastDetailViewModel(private val movieCastDao: MovieCastDao) : ViewModel() {

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
    fun getMoviesForCast(personId: Int) : LiveData<List<MovieItem>>{
        return movieCastDao.getMoviesForCast(personId)
    }

}