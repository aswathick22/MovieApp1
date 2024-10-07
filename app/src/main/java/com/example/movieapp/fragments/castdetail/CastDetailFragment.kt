package com.example.movieapp.fragments.castdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentCastDetailBinding
import com.example.movieapp.fragments.castdetail.adapter.CastDetailAdapter
import com.example.movieapp.fragments.moviedetail.MovieDetailFragmentDirections
import com.example.movieapp.fragments.moviedetail.adapter.CastListAdapter
import com.example.movieapp.remote.api.MovieDBClient
import com.squareup.picasso.Picasso

class CastDetailFragment : Fragment() {

    private val castViewModel by viewModels<CastDetailViewModel>()
    private lateinit var castDetailBinding: FragmentCastDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        castDetailBinding = FragmentCastDetailBinding.inflate(inflater, container, false)
        return castDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val personId = arguments?.getInt("personId")
        castViewModel.updateMovieId(personId ?: 0)
        castViewModel.castDetail.observe(viewLifecycleOwner) { castdetails ->
            Picasso.get().load(MovieDBClient.POSTER_BASE_URL + castdetails.profilePath)
                .placeholder(R.drawable.poster_placeholder)
                .noFade()
                .into(castDetailBinding.castImage)
            castDetailBinding.castName.text = castdetails.name
            castDetailBinding.castKnownFor.text = castdetails.knownForDepartment
            castDetailBinding.castGender.text = getGenderText(castdetails.gender)
            castDetailBinding.castBirthday.text = castdetails.birthday
            castDetailBinding.castPlaceOfBirth.text = castdetails.placeOfBirth
            castDetailBinding.castBiography.text = castdetails.biography
        }

        castViewModel.getMoviesForCast(personId ?: 0).observe(viewLifecycleOwner) {movies ->
            castDetailBinding.retrofitRecyclerview.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = CastDetailAdapter(movies)
            }
        }
    }

    private fun getGenderText(gender: Int?): String {
        return when (gender) {
            1 -> "Female"
            2 -> "Male"
            else -> "Unknown"
        }
    }

}








