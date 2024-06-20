package com.example.movieapp1.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp1.data.api.MovieDBInterface
import com.example.movieapp1.data.movie.MovieDetails


class MovieDetailsNetworkDataSource(private val apiService : MovieDBInterface) {

    /*private val _networkState = MutableLiveData<LiveData<NetworkState>>()
    val networkState : MutableLiveData<LiveData<NetworkState>> get() = _networkState

    private val _movieDetailsResponse = MutableLiveData<LiveData<MovieDetails>>()
    val movieDetailsResponse : MutableLiveData<LiveData<MovieDetails>> get() = _movieDetailsResponse

    fun fetchMovieDetails(movieId : Int){
        _networkState.postValue(NetworkState.LOADING)

        try {
                compositeDecomposable.add(
                    apiService.getMovieDetails(movieId)
                        .suscribeOn(Schedulers.io())
                        .suscribe(
                            {
                                _movieDetailsResponse.postValue(it)
                                _networkState.postValue(NetworkState.LOADED)
                            },
                            {
                                _networkState.postValue(NetworkState.ERROR)
                                Log.e("MovieDetailsNetworkDataSource", it.message)
                            }
                )
        }
        catch (e : Exception){
            Log.e("MovieDetailsNetworkDataSource", e.message)
        }
    }*/
}