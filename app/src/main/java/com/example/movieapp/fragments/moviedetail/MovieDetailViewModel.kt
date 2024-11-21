package com.example.movieapp.fragments.moviedetail

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.database.DatabaseHandler
import com.example.movieapp.database.roomdatabase.data.UserListRepository
import com.example.movieapp.remote.api.MovieDBClient
import com.example.movieapp.remote.data.Buy
import com.example.movieapp.remote.data.MovieCastList
import com.example.movieapp.remote.data.MovieDetails
import com.example.movieapp.remote.data.Rent
import com.example.movieapp.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val userListRepository: UserListRepository) : ViewModel() {

    private val movieIdLiveData = MutableLiveData(0)

    private val _movieDetail = MutableLiveData<MovieDetails>()
    val movieDetail : MutableLiveData<MovieDetails> get() = _movieDetail

    private val _castList = MutableLiveData<MovieCastList>()
    val castList : MutableLiveData<MovieCastList> get() = _castList

    private val _buyMovie = MutableLiveData<Buy>()
    val buyMovie : MutableLiveData<Buy> get() = _buyMovie

    private val _rentMovie = MutableLiveData<Rent>()
    val rentMovie : MutableLiveData<Rent> get() = _rentMovie

    private val _username = MutableLiveData<String>()
    val username: MutableLiveData<String> get() = _username

    private val _phone = MutableLiveData<String>()
    val phone: MutableLiveData<String> get() = _phone

    private val _email = MutableLiveData<String>()
    val email: MutableLiveData<String> get() = _email

    private lateinit var dbHandler: DatabaseHandler


    /*private val _userLists = MutableLiveData<List<UserList>>()
    val userLists: MutableLiveData<List<UserList>> get() = _userLists

    fun getUserLists(userId: Int) {
        viewModelScope.launch {
            val lists = userListRepository.getListsForUser(userId)
            _userLists.postValue(lists)
        }
    }

    fun addMovieToList(listId: Int, movieId: Int) {
        viewModelScope.launch {
            userListRepository.addMovieToList(listId, movieId)
        }
    }*/
    fun fetchUser(context : Context, username: String) {
        dbHandler = DatabaseHandler(context)
        val user = dbHandler.fetchUser(username)
        if(user!=null) {
            _phone.value = user["phone"]
            _email.value = user["email"]
            Log.d("AccountViewModel", "Fetched User: $user")
        }
        else{
            Log.d("AccountViewModel", "User not found")
        }
    }

    fun updateMovieId(movieId : Int){
        movieIdLiveData.value = movieId
        val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
        viewModelScope.launch {
            _movieDetail.value = repository.getMovieDetails(movieIdLiveData.value?:0)
            _castList.value = repository.getCastList(movieIdLiveData.value?:0)
            _buyMovie.value = repository.buyMovie(movieIdLiveData.value?:0)
            _rentMovie.value = repository.rentMovie(movieIdLiveData.value?:0)
        }
    }

}