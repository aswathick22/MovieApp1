package com.example.movieapp.database.roomdatabase.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movieapp.database.DatabaseHandler
import com.example.movieapp.remote.data.MovieDetails
import com.example.movieapp.remote.data.MovieItem
import com.example.movieapp.repository.MovieRepository
import kotlinx.coroutines.launch

class UserListViewModel (private val userListRepository: UserListRepository) : ViewModel() {

    private val user = MutableLiveData<Map<String, String>>()
    val lists = MutableLiveData<List<ListWithMovies>>()
    private val movies = MutableLiveData<List<MovieItem>>()

    fun fetchUser(username : String) {
        user.value = userListRepository.getUser(username)
    }

    private fun fetchLists(userId: Int) {
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

    fun addMovieToList(listId: Int, movieId: MovieDetails) {
        viewModelScope.launch {
            userListRepository.addMovieToList(listId, movieId)
            fetchMoviesForList(listId)
        }
    }

    private fun fetchMoviesForList(listId: Int) {
        viewModelScope.launch {
            val movieList = userListRepository.getMoviesForList(listId)
            movies.value = movieList
        }
    }
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

        /*companion object {
            @Volatile
            private var instance: ViewModelFactory? = null

            fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    UserListRepository(
                        UserRepository(DatabaseHandler(context)),
                        ListRepository(MovieRoomDatabase.getInstance(context).userListDao(), MovieRepository.getInstance())
                    )
                ).also { instance = it }
            }
        }*/
    }

