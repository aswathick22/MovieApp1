package com.example.movieapp.database.roomdatabase.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movieapp.remote.data.MovieItem
import kotlinx.coroutines.launch

class UserListViewModel (private val userListRepository: UserListRepository) : ViewModel() {

    val lists = MutableLiveData<List<UserList>>()
    private val movies = MutableLiveData<List<MovieItem>>()

    fun addListForUser(userList: UserList) {
        viewModelScope.launch {
            userListRepository.addListForUser(userList)
            fetchLists(userList.userId)
        }
    }

    fun fetchLists(userId: Int?) {
        viewModelScope.launch {
            lists.value = userListRepository.getListsForUser(userId)
        }
    }

    fun addMovieToList(listId: Int, movieId: Int) {
        viewModelScope.launch {
            userListRepository.addMovieToList(listId, movieId)
            fetchMoviesForList(listId)
        }
    }

    private fun fetchMoviesForList(listId: Int) {
        viewModelScope.launch {
            movies.value = userListRepository.getMoviesForList(listId)
        }
    }

    fun deleteList(listId: Int) {
        viewModelScope.launch {
            userListRepository.deleteList(listId)
        }
    }

    fun clearAllLists(userId: Int) {
        viewModelScope.launch {
            userListRepository.clearAllLists(userId)
        }
    }


    /*private val user = MutableLiveData<List<UserList>>()
    val lists = MutableLiveData<List<UserList>>()
    private val movies = MutableLiveData<List<MovieItem>>()

    fun fetchUser(username : String) {
        user.value = userListRepository.getUser(username)
    }

    private fun fetchLists(userId: String) {
        viewModelScope.launch {
            lists.value = userListRepository.getListsForUser(userId)
        }
    }

    fun addList(userList: UserList) {
        viewModelScope.launch {
            userListRepository.addListForUser(userList)
            fetchLists(userList.userId)
        }
    }

    fun addMovieToList(list: Int, movieId: Int) {
        viewModelScope.launch {
            userListRepository.addMovieToList(list, movieId)
            fetchMoviesForList(list, movieId)
        }
    }

    private fun fetchMoviesForList(listId: Int, movieId: Int) {
        viewModelScope.launch {
            val movieList = userListRepository.getMoviesForList(listId, movieId)
            movies.value = movieList
        }
    }*/
    }

    class ViewModelFactory(private val userListRepository: UserListRepository) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
                UserListViewModel(userListRepository) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }

