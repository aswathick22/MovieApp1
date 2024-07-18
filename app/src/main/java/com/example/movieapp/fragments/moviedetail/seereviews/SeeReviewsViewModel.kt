/*
package com.example.movieapp.fragments.moviedetail.seereviews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.remote.api.MovieDBClient
import com.example.movieapp.remote.data.MovieCastList
import com.example.movieapp.remote.data.MovieReview
import com.example.movieapp.repository.MovieRepositoryImpl
import kotlinx.coroutines.launch

class SeeReviewsViewModel : ViewModel() {

        private val movieIdLiveData = MutableLiveData(0)

        private val _reviewDetail = MutableLiveData<MovieReview>()
        val reviewDetail : MutableLiveData<MovieReview> get() = _reviewDetail

        private val _castList = MutableLiveData<MovieCastList>()
        val authorList : MutableLiveData<MovieCastList> get() = _castList

        fun updateMovieId(movieId : Int){
            movieIdLiveData.value = movieId
            val repository = MovieRepositoryImpl(MovieDBClient.movieDBInterface)
            viewModelScope.launch {
                _reviewDetail.value = repository.getMovieReviews(movieIdLiveData.value?:0)
                _castList.value = repository.getCastList(movieIdLiveData.value?:0)
            }
        }

    }
}*/
