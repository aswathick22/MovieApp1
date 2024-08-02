package com.example.movieapp.fragments.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
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
import com.example.movieapp.database.DatabaseHandler
import com.example.movieapp.database.SharedPreferencesManager
import com.example.movieapp.database.roomdatabase.data.MovieDetailViewModelFactory
import com.example.movieapp.database.roomdatabase.data.UserList
import com.example.movieapp.database.roomdatabase.data.UserListRepository
import com.example.movieapp.database.roomdatabase.data.UserListViewModel
import com.example.movieapp.database.roomdatabase.data.UserListViewModelFactory
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.fragments.moviedetail.adapter.CastListAdapter
import com.example.movieapp.remote.api.MovieDBClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class MovieDetailFragment : Fragment() {

    private lateinit var movieDetailBinding: FragmentMovieDetailBinding
    private lateinit var dbHandler: DatabaseHandler
    private val movieDetailViewModel: MovieDetailViewModel by viewModels {
        MovieDetailViewModelFactory(UserListRepository(requireContext()))
    }
    private val userListViewModel: UserListViewModel by viewModels {
        UserListViewModelFactory(UserListRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieDetailBinding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        dbHandler = DatabaseHandler(requireContext())
        return movieDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = SharedPreferencesManager.getUserId(requireContext())
        userListViewModel.fetchLists(userId)

        userListViewModel.lists.observe(viewLifecycleOwner) { lists ->
            if (lists.isNullOrEmpty()) {
                Log.d("MovieDetailFragment", "No lists found for userId: $userId")
            } else {
                Log.d("MovieDetailFragment", "Lists found: ${lists.size}")
            }
            val movieId = arguments?.getInt("movieId")
            setupFab(movieDetailBinding.addActionButton, lists, movieId)
        }

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

        val movieId = arguments?.getInt("movieId")
        movieDetailViewModel.updateMovieId(movieId ?: 0)
        movieDetailViewModel.movieDetail.observe(viewLifecycleOwner) { moviedetails ->
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
        movieDetailViewModel.castList.observe(viewLifecycleOwner) { items ->
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
    }

    private fun addMovieToSelectedLists(
        lists: List<UserList>,
        checkedItems: BooleanArray,
        movieId: Int?
    ) {
        movieId?.let {
            lists.forEachIndexed { index, userList ->
                if (checkedItems[index]) {
                    userListViewModel.addMovieToList(userList.listId, movieId)
                }
            }
        }
    }

    private fun showAddToListDialog(lists: List<UserList>, movieId: Int?) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("Add movie to list")

        if (lists.isEmpty()) {
            // When no lists are created
            val messageView = LayoutInflater.from(requireContext()).inflate(R.layout.item_no_lists, null)
            dialogBuilder.setView(messageView)
        } else {
            // When lists are available
            val listView = LayoutInflater.from(requireContext()).inflate(R.layout.item_user_lists, null)
            val listContainer = listView.findViewById<LinearLayout>(R.id.list_container)
            val checkedItems = BooleanArray(lists.size)

            lists.forEachIndexed { index, list ->
                val checkBox = CheckBox(requireContext()).apply {
                    text = list.listName
                    setOnCheckedChangeListener { _, isChecked ->
                        checkedItems[index] = isChecked
                    }
                }
                listContainer.addView(checkBox)
            }

            dialogBuilder.setView(listView)
            dialogBuilder.setPositiveButton("Add") { _, _ ->
                addMovieToSelectedLists(lists, checkedItems, movieId)
            }
        }

        dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        dialogBuilder.create().show()
    }

    private fun setupFab(addToListFab: FloatingActionButton, lists: List<UserList>, movieId: Int?) {
        addToListFab.setOnLongClickListener {
            TooltipCompat.setTooltipText(addToListFab, getString(R.string.add_movie_to_list))
            true
        }

        addToListFab.setOnClickListener {
            showAddToListDialog(lists, movieId)
        }
    }
}
























