package com.example.movieapp.fragments.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.fragments.movielist.adapter.MovieListAdapter

class MovieListFragment : Fragment() {

    private val mainViewModel by viewModels<MovieListViewModel>()

    private lateinit var movieListBinding : FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        movieListBinding = FragmentMovieListBinding.inflate(inflater,container,false)
        return movieListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.movieList.observe(viewLifecycleOwner) { items ->
            movieListBinding.retrofitRecyclerview.apply {
                layoutManager = GridLayoutManager(context,2)
                adapter = MovieListAdapter(items.results){
                    findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(it.id))
                }
            }
        }
    }

}