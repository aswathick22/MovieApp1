package com.example.movieapp.database.roomdatabase.data

import android.content.Context
import com.example.movieapp.remote.data.MovieItem

class UserListRepository(context: Context) {
    private val userListDao = MovieRoomDatabase.getInstance(context).userListDao()

    fun getUser(username: String): List<UserList> {
        return userListDao.getUser(username)
    }

    suspend fun getListsForUser(userId: String): List<UserList> {
        return userListDao.getListsForUser(userId)
    }

    suspend fun addListForUser(userList: UserList) {
        userListDao.insertList(userList)
    }

    suspend fun addMovieToList(list: UserList) {
        userListDao.insertMovie(list)
    }

    suspend fun getMoviesForList(listId: UserList): List<MovieItem> {
        return userListDao.getMoviesForList(listId)
    }
}

