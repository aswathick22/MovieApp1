package com.example.movieapp1.data.api

import com.example.movieapp1.R

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

class MovieActivity : AppCompatActivity() {

    private val movieViewModel by viewModels<MovieViewModel>()
    private val movieRepository : MovieDetailsRepository

    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_movie, container, false)
    }

    fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieView: GridView = view.findViewById(R.id.)
        movieView.layoutManager = LinearLayoutManager(context)

        movieViewModel.movieId.observe(viewLifecycleOwner){ items ->
            movieView.adapter = MovieAdapter(items)
        }

    }

}