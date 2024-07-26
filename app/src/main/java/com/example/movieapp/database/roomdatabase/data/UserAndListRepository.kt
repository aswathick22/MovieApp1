package com.example.movieapp.database.roomdatabase.data

import UserListDao
import com.example.movieapp.database.DatabaseHandler
import com.example.movieapp.remote.data.MovieDetails
import com.example.movieapp.remote.data.MovieItem
import com.example.movieapp.repository.MovieRepository

class UserRepository(private val dbHelper: DatabaseHandler) {

    fun getUser(username: String): Map<String, String>? {
        return dbHelper.fetchUser(username)
    }
    // Other user-related methods
}

class ListRepository(private val userListDao: UserListDao, private val remoteDataSource: MovieRepository) {

    suspend fun insertList(userList: UserList) {
        userListDao.insertList(userList)
    }

    suspend fun getListsForUser(userId: Int): List<ListWithMovies> {
        return userListDao.getListsForUser(userId)
    }

    suspend fun addMovieToList(listId: Int, movie: MovieDetails) {
        userListDao.insertMovie(movie)
        userListDao.insertListMovieCrossRef(ListMovieCrossRef(listId, movie.id))
    }

    suspend fun getMoviesForList(listId: Int): List<MovieItem> {
        return userListDao.getMoviesForList(listId)
    }
}
