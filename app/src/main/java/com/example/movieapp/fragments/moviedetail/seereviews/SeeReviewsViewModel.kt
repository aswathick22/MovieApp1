package com.example.movieapp.fragments.moviedetail.seereviews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.remote.api.MovieDBClient
import com.example.movieapp.remote.data.MovieReview
import com.example.movieapp.remote.data.ResultXX
import com.example.movieapp.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch

class SeeReviewsViewModel : ViewModel() {

    private val movieIdLiveData = MutableLiveData(0)

    private val _reviewDetail = MutableLiveData<MovieReview>()
    val reviewDetail: MutableLiveData<MovieReview> get() = _reviewDetail

    private val _reviews = MutableLiveData<List<ResultXX>>()
    val reviews: MutableLiveData<List<ResultXX>> get() = _reviews

    fun updateMovieId(movieId: Int) {
        movieIdLiveData.value = movieId
        val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)

        viewModelScope.launch {
            val response = repository.getMovieReviews(movieIdLiveData.value ?: 0)
            _reviewDetail.value = response
            _reviews.value = response.results
        }
    }

}
