package com.example.movieapp.fragments.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.TooltipCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.database.roomdatabase.data.UserList
import com.example.movieapp.database.roomdatabase.data.UserListViewModel
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.fragments.moviedetail.adapter.CastListAdapter
import com.example.movieapp.remote.api.MovieDBClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class MovieDetailFragment : Fragment() {

    private val mainViewModel by viewModels<MovieDetailViewModel>()
    private lateinit var movieDetailBinding: FragmentMovieDetailBinding
    private val userListViewModel by viewModels<UserListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieDetailBinding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return movieDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_movie_detail, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu item selection
                return when (menuItem.itemId) {
                    R.id.watch_video -> {
                        val movieId = arguments?.getInt("movieId") ?: 0
                        findNavController().navigate(
                            MovieDetailFragmentDirections.actionMovieDetailFragmentToWatchTrailerFragment(
                                movieId
                            )
                        )
                        true
                    }

                    R.id.see_reviews -> {
                        val movieId = arguments?.getInt("movieId") ?: 0
                        findNavController().navigate(
                            MovieDetailFragmentDirections.actionMovieDetailFragmentToSeeReviewsFragment(
                                movieId
                            )
                        )
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


        fun showAddToListDialog() {
            val username = ""
            userListViewModel.fetchUser(username)

            userListViewModel.lists.observe(viewLifecycleOwner) { lists ->
                val checkedItems = BooleanArray(lists.size)
                val listTitles = lists!!.map { it.listName }.toTypedArray()
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Add movie to list")
                    /*.setMultiChoiceItems(listTitles, checkedItems, DialogInterface.OnMultiChoiceClickListener
                    {
                            _, which, isChecked -> checkedItems[which] = isChecked })*/
                    builder.setPositiveButton("Add") { _, _ ->
                        val movieId = arguments?.getInt("movieId")
                        addMovieToSelectedLists(lists, checkedItems, movieId)
                    }
                    builder.setNegativeButton("Cancel", null)
                    builder.create()
                    builder.show()
            }
        }


        val movieId = arguments?.getInt("movieId")
        mainViewModel.updateMovieId(movieId ?: 0)
        mainViewModel.movieDetail.observe(viewLifecycleOwner) { moviedetails ->
            Picasso.get().load(MovieDBClient.POSTER_BASE_URL + moviedetails.posterPath)
                .placeholder(R.drawable.poster_placeholder)
                .noFade()
                .into(movieDetailBinding.movieImage)
            movieDetailBinding.movieTitle.text = moviedetails.title
            movieDetailBinding.movieRating.text = moviedetails.voteAverage.toString()
            movieDetailBinding.movieDescription.text = moviedetails.overview
            movieDetailBinding.movieLanguage.text = moviedetails.originalLanguage
            movieDetailBinding.movieRuntime.text = moviedetails.runtime.toString()
            movieDetailBinding.movieReleaseDate.text = moviedetails.runtime.toString()
        }
        mainViewModel.castList.observe(viewLifecycleOwner) { items ->
            movieDetailBinding.retrofitRecyclerview.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = CastListAdapter(items.cast) {
                    findNavController().navigate(
                        MovieDetailFragmentDirections.actionMovieDetailFragmentToCastDetailFragment(
                            it.id
                        )
                    )
                }
            }
        }

        val addToListFab: FloatingActionButton = movieDetailBinding.addActionButton
        addToListFab.setOnLongClickListener {
            val tooltip =
                TooltipCompat.setTooltipText(addToListFab, getString(R.string.add_movie_to_list))
            tooltip
            true
        }

        addToListFab.setOnClickListener {
            showAddToListDialog()
        }
    }

    private fun addMovieToSelectedLists(lists: List<UserList>, checkedItems: BooleanArray, movieId: Int?) {
        lists.forEachIndexed { index, userList ->
            if (checkedItems[index]) {
                userListViewModel.addMovieToList(userList.listId, movieId)
            }
        }
    }
}
















