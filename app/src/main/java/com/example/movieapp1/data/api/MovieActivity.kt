package com.example.movieapp1.data.api

import com.example.movieapp1.R

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.JsonAdapter

class MovieActivity : AppCompatActivity() {

    private val movieViewModel by viewModels<MovieViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        movieViewModel.movieList.observe(this){
            it.results.forEach{movie ->
                println("Name of movie : ${movie.title}")
            }
        }
    }


}