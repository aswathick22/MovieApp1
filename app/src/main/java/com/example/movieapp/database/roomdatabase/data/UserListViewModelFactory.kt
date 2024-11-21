package com.example.movieapp.database.roomdatabase.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserListViewModelFactory(
    private val userListRepository: UserListRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            UserListViewModel(userListRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
