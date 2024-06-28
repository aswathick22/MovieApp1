package com.example.movieapp.fragments.castlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.fragments.castlist.adapter.CastListAdapter

class CastListFragment : Fragment() {

    private val castViewModel by viewModels<CastListViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var castListBinding : FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        castListBinding = FragmentMovieDetailBinding.inflate(inflater,container,false)
        return castListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = arguments?.getInt("movieId")
        castViewModel.updateMovieId(movieId?:0)
        castViewModel.castList.observe(viewLifecycleOwner) { items ->
            castListBinding.retrofitRecyclerview.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                /*recyclerView.layoutManager = layoutManager*/
                adapter = CastListAdapter(items.cast) { item ->
                    findNavController().navigate(item.id)
                }
                /*recyclerView.adapter = adapter*/
            }
        }
    }

}