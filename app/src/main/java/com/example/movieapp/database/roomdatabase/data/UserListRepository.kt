package com.example.movieapp.database.roomdatabase.data

import com.example.movieapp.remote.data.MovieDetails
import com.example.movieapp.remote.data.MovieItem

class UserListRepository(
    private val userRepository: UserRepository,
    private val listRepository: ListRepository
)
{
    fun getUser(username: String): Map<String, String>? {
        return userRepository.getUser(username)
    }

    suspend fun getListsForUser(userId: Int): List<ListWithMovies> {
        return listRepository.getListsForUser(userId)
    }

    suspend fun addListForUser(userList: UserList) {
        listRepository.insertList(userList)
    }

    suspend fun addMovieToList(listId: Int, movieId: MovieDetails) {
        listRepository.addMovieToList(listId, movieId)
    }

    suspend fun getMoviesForList(listId: Int) : List<MovieItem> {
        return listRepository.getMoviesForList(listId)
    }

}

