package com.example.movieapp.database.roomdatabase.data

import android.content.Context
import com.example.movieapp.remote.data.MovieItem

class UserListRepository(context: Context) {
    private val userListDao = MovieRoomDatabase.getInstance(context).userListDao()

    fun getUser(username: String): Map<String, String>? {
        return userListDao.getUser(username)
    }

    suspend fun getListsForUser(userId: String): List<UserList> {
        return userListDao.getListsForUser(userId)
    }

    suspend fun addListForUser(userList: UserList) {
        userListDao.insertList(userList)
    }

    suspend fun addMovieToList(listId: Int, movieId: Int?) {
        userListDao.insertMovie(listId, movieId)
    }

    suspend fun getMoviesForList(listId: Int): List<MovieItem> {
        return userListDao.getMoviesForList(listId)
    }
}

