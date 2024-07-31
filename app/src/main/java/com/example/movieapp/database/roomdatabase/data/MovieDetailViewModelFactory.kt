package com.example.movieapp.database.roomdatabase.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.fragments.moviedetail.MovieDetailViewModel

class MovieDetailViewModelFactory(
    private val userListRepository: UserListRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            MovieDetailViewModel(userListRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
