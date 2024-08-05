package com.example.movieapp.database.roomdatabase.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    fun fetchLists(userId: Int) {
        viewModelScope.launch {
            val list = userListRepository.getListsForUser(userId)
            lists.postValue(list)
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
}







