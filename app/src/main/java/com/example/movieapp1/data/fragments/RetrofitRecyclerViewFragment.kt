package com.example.movieapp1.data.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp1.R
import com.example.movieapp1.data.api.MovieViewModel
import com.example.movieapp1.data.api.MovieAdapter

class RetrofitRecyclerViewFragment : Fragment() {

    private val mainViewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.retrofit_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.retrofit_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)

        mainViewModel.movieList.observe(viewLifecycleOwner){ items ->
            recyclerView.adapter = MovieAdapter(items.results)
        }
    }

}