package com.example.movieapp.fragments.moviedetail.seereviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.databinding.FragmentSeeReviewsBinding
import com.example.movieapp.fragments.moviedetail.MovieDetailFragmentDirections
import com.example.movieapp.fragments.moviedetail.MovieDetailViewModel
import com.example.movieapp.fragments.moviedetail.adapter.CastListAdapter
import com.example.movieapp.remote.api.MovieDBClient
import com.squareup.picasso.Picasso

class SeeReviewsFragment : Fragment() {

        private val seeReviewsViewModel by viewModels<SeeReviewsViewModel>()
        private lateinit var seeReviewsBinding: FragmentSeeReviewsBinding

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            seeReviewsBinding = FragmentSeeReviewsBinding.inflate(inflater, container, false)
            return seeReviewsBinding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

          /*  val movieId = arguments?.getInt("movieId")
            seeReviewsViewModel.updateMovieId(movieId ?: 0)
            seeReviewsViewModel.reviewDetail.observe(viewLifecycleOwner) { reviewdetails ->
                seeReviewsBinding.authorName.text = reviewdetails.author
                seeReviewsBinding.reviewContentText.text = reviewdetails.
            }*/
        }
    }












}