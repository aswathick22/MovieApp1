package com.example.movieapp.database.roomdatabase.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.remote.data.MovieItem

class UserListRepository(context: Context) {
    private val userListDao = MovieRoomDatabase.getInstance(context).userListDao()

    suspend fun getListsForUser(userId: Int): List<UserList> {
        return userListDao.getListsForUser(userId)
    }

    suspend fun addListForUser(userList: UserList) {
        userListDao.insertList(userList)
    }

    suspend fun addMovieToList(listId: Int, movieId: Int) {
        val crossRef = ListMovieCrossRef(listId, movieId)
        userListDao.insertListMovieCrossRef(crossRef)
    }

    suspend fun getMoviesForList(listId: Int): List<MovieItem> {
        return userListDao.getMoviesForList(listId)
    }

    suspend fun deleteList(listId: Int) {
        userListDao.deleteList(listId)
    }

    suspend fun clearAllLists(userId: Int) {
        userListDao.clearAllLists(userId)
    }

}

