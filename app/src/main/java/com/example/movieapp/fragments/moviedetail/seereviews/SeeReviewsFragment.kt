package com.example.movieapp.fragments.moviedetail.seereviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentSeeReviewsBinding
import com.example.movieapp.fragments.moviedetail.adapter.SeeReviewAdapter

class SeeReviewsFragment : Fragment() {

    private val seeReviewsViewModel by viewModels<SeeReviewsViewModel>()
    private lateinit var seeReviewsBinding: FragmentSeeReviewsBinding
    private lateinit var adapter: SeeReviewAdapter

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

        val movieId = arguments?.getInt("movieId")
        seeReviewsViewModel.updateMovieId(movieId ?: 0)

        adapter = SeeReviewAdapter(
            emptyList(),
            onShowMoreClick = { textView, _ -> textView.maxLines = Int.MAX_VALUE },
            onShowLessClick = { textView, _ -> textView.maxLines = 3 }
        )

        seeReviewsBinding.reviewsRecyclerview.layoutManager = LinearLayoutManager(context)
        seeReviewsBinding.reviewsRecyclerview.adapter = adapter

        seeReviewsViewModel.reviewDetail.observe(viewLifecycleOwner) { reviewDetails ->
            adapter.updateData(reviewDetails.results)
        }

        /*seeReviewsViewModel.reviewDetail.observe(viewLifecycleOwner) { reviewdetails ->
            seeReviewsBinding.reviewsRecyclerview.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = SeeReviewAdapter(reviewdetails.results)
            }
        }*/
    }
}