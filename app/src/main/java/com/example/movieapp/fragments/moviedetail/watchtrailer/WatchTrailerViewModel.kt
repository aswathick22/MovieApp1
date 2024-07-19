package com.example.movieapp.fragments.moviedetail.watchtrailer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.remote.api.MovieDBClient
import com.example.movieapp.remote.data.MovieVideos
import com.example.movieapp.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch

class WatchTrailerViewModel : ViewModel() {

    private val _youtubeKey = MutableLiveData<String>()
    val youtubeKey: LiveData<String> get() = _youtubeKey

    fun fetchMovieTrailer(movieId: Int) {
        viewModelScope.launch {
            try {
                val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
                val movieVideos: MovieVideos = repository.getMovieTrailer(movieId)
                // Get the key of the first available video
                val videoKey = movieVideos.results.firstOrNull()?.key
                _youtubeKey.value = videoKey ?: ""
            } catch (e: Exception) {
                // Handle any errors
                _youtubeKey.value = ""
            }
        }
    }
}


