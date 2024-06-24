package com.example.movieapp.fragments.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.fragments.moviedetail.adapter.MovieDetailAdapter

class MovieDetailFragment : Fragment() {

    //private val mainViewModel by viewModels<MovieDetailViewModel>()
    //private lateinit var MovieDetailBinding : FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)

        //MovieDetailBinding = FragmentMovieDetailBinding.inflate(inflater,container,false)
        //return MovieDetailBinding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

        /*mainViewModel.movieDetail.observe(viewLifecycleOwner) { items ->
            movieDetailBinding.retrofitRecyclerview.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = MovieDetailAdapter(items.results)
            }*/






