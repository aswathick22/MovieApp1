package com.example.movieapp.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.fragments.movielist.MovieListFragment
import com.example.movieapp.fragments.movielist.adapter.MovieListAdapter

class HomeFragment : Fragment() {

    private lateinit var homeBinding : FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
/*        val action = HomeFragmentDirections.action_homeFragment_to_movieDetailFragment
        findNavController().navigate(action)*/
        homeBinding = FragmentHomeBinding.inflate(inflater,container,false)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        homeBinding.bottomNavigation.setupWithNavController(navController)
        return homeBinding.root
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }*/

}
