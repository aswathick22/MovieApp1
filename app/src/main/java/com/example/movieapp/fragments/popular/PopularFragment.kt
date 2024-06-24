package com.example.movieapp.fragments.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentPopularBinding

class PopularFragment : Fragment() {

    private lateinit var popularBinding : FragmentPopularBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        popularBinding = FragmentPopularBinding.inflate(inflater,container,false)
        /*val navHostFragment1 = childFragmentManager.findFragmentById(R.id.nav_host_fragment_1) as NavHostFragment
        val navController = navHostFragment1.navController
        popularBinding.bottomNavigation1.setupWithNavController(navController)*/
        return popularBinding.root
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }*/

}