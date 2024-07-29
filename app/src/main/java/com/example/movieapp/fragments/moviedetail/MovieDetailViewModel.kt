package com.example.movieapp.fragments.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.database.roomdatabase.data.UserList
import com.example.movieapp.database.roomdatabase.data.UserListRepository
import com.example.movieapp.remote.api.MovieDBClient
import com.example.movieapp.remote.data.MovieCastList
import com.example.movieapp.remote.data.MovieDetails
import com.example.movieapp.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: UserListRepository) : ViewModel() {

    private val movieIdLiveData = MutableLiveData(0)

    private val _movieDetail = MutableLiveData<MovieDetails>()
    val movieDetail : MutableLiveData<MovieDetails> get() = _movieDetail

    private val _castList = MutableLiveData<MovieCastList>()
    val castList : MutableLiveData<MovieCastList> get() = _castList

    fun getUserLists(userId: String, onResult: (List<UserList>) -> Unit) {
        viewModelScope.launch {
            val lists = repository.getListsForUser(userId)
            onResult(lists)
        }
    }

    fun addMovieToList(listId: Int, movieId: Int?) {
        viewModelScope.launch {
            repository.addMovieToList(listId, movieId)
        }
    }

    fun updateMovieId(movieId : Int){
        movieIdLiveData.value = movieId
        val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
        viewModelScope.launch {
            _movieDetail.value = repository.getMovieDetails(movieIdLiveData.value?:0)
            _castList.value = repository.getCastList(movieIdLiveData.value?:0)
        }
    }

}