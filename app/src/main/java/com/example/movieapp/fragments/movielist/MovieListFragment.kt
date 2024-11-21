package com.example.movieapp.fragments.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
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
                movieListBinding.retrofitRecyclerview.visibility = View.VISIBLE
                layoutManager = GridLayoutManager(context,2)
                adapter = MovieListAdapter(items.results){
                    findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(it.id,))
                }
            }
        }

        movieListBinding.tvSearchAllMovies.addTextChangedListener{
            if(it.toString().isNotEmpty())
            {
                movieListBinding.retrofitRecyclerview.visibility = View.GONE
                mainViewModel.getAllSearchResults(it.toString())
            }
        }

        mainViewModel.allSearchResults.observe(viewLifecycleOwner) { items ->
            movieListBinding.retrofitRecyclerview.apply {
                movieListBinding.retrofitRecyclerview.visibility = View.VISIBLE
                layoutManager = GridLayoutManager(context,2)
                adapter = MovieListAdapter(items.results){
                    findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(it.id))
                }
            }
        }

        mainViewModel.filterClicked.observe(viewLifecycleOwner) {
            when(it){
                0 -> {
                    movieListBinding.tvAllMovies.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
                    movieListBinding.tvTopRatedMovies.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.purple_500))
                    movieListBinding.tvUpcomingMovies.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.purple_500))
                }
                1 -> {
                    movieListBinding.tvAllMovies.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.purple_500))
                    movieListBinding.tvTopRatedMovies.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
                    movieListBinding.tvUpcomingMovies.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.purple_500))
                }
                2 -> {
                    movieListBinding.tvAllMovies.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.purple_500))
                    movieListBinding.tvTopRatedMovies.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.purple_500))
                    movieListBinding.tvUpcomingMovies.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
                }
            }
        }

        movieListBinding.tvAllMovies.setOnClickListener{
            mainViewModel.filterClicked.value = 0
            movieListBinding.tvSearchAllMovies.setText("")
            movieListBinding.retrofitRecyclerview.visibility = View.VISIBLE
            mainViewModel.getAllMovieLists()
        }

        movieListBinding.tvTopRatedMovies.setOnClickListener{
            mainViewModel.filterClicked.value = 1
            movieListBinding.tvSearchAllMovies.setText("")
            movieListBinding.retrofitRecyclerview.visibility = View.GONE
            mainViewModel.getTopRatedMoviesList()
        }

        mainViewModel.topRatedMovieList.observe(viewLifecycleOwner) {
            movieListBinding.retrofitRecyclerview.apply {
                movieListBinding.retrofitRecyclerview.visibility = View.VISIBLE
                layoutManager = GridLayoutManager(context,2)
                adapter = MovieListAdapter(it.results){
                    findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(it.id))
                }
            }
        }

        movieListBinding.tvUpcomingMovies.setOnClickListener{
            mainViewModel.filterClicked.value = 2
            movieListBinding.tvSearchAllMovies.setText("")
            movieListBinding.retrofitRecyclerview.visibility = View.GONE
            mainViewModel.getUpcomingMoviesList()
        }

        mainViewModel.upcomingMovieList.observe(viewLifecycleOwner) { items ->
            movieListBinding.retrofitRecyclerview.apply {
                movieListBinding.retrofitRecyclerview.visibility = View.VISIBLE
                layoutManager = GridLayoutManager(context,2)
                adapter = MovieListAdapter(items.results){
                    findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(it.id))
                }
            }
        }


    }

}