package com.example.movieapp1.data.activities

import com.example.movieapp1.R

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.movieapp1.data.api.MovieViewModel

class MovieActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val movieViewModel by viewModels<MovieViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        movieViewModel.movieList.observe(this){movies ->
            movies.results.forEach{movie ->
                println("Name of movie : ${movie.title}")
            }
        }

        movieViewModel.movieList.observe(this){insideout->
            insideout.results.forEach{movie ->
                println("Name of movie : ${movie.title}")
            }
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}