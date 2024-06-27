package com.example.movieapp.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var activityMovieBinding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMovieBinding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(activityMovieBinding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}