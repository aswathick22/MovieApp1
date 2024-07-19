package com.example.movieapp.fragments.moviedetail.watchtrailer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movieapp.databinding.FragmentWatchTrailerBinding

class WatchTrailerFragment : Fragment() {

        private lateinit var binding: FragmentWatchTrailerBinding
        private val watchTrailerViewModel by viewModels<WatchTrailerViewModel>()

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            binding = FragmentWatchTrailerBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val movieId = arguments?.getInt("movieId") ?: 0
            watchTrailerViewModel.fetchMovieTrailer(movieId)
            watchTrailerViewModel.youtubeKey.observe(viewLifecycleOwner) { youtubeKey ->
                val youtubeUrl = "https://www.youtube.com/embed/$youtubeKey"
                binding.webView.apply {
                    webViewClient = WebViewClient()
                    webChromeClient = WebChromeClient()
                    settings.javaScriptEnabled = true
                    loadUrl(youtubeUrl)
                }
            }
        }
    }
