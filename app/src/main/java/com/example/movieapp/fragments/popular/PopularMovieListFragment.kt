package com.example.movieapp.fragments.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.databinding.FragmentPopularMovieListBinding
import com.example.movieapp.fragments.popular.adapter.PopularMovieListAdapter

class PopularMovieListFragment : Fragment() {

    private val mainViewModel by viewModels<PopularMovieListViewModel>()

    private lateinit var popularMovieListBinding : FragmentPopularMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        popularMovieListBinding = FragmentPopularMovieListBinding.inflate(inflater,container,false)
        return popularMovieListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.popularMovieList.observe(viewLifecycleOwner) { items ->
            popularMovieListBinding.retrofitRecyclerview.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = PopularMovieListAdapter(items.results)
            }
        }
    }

}