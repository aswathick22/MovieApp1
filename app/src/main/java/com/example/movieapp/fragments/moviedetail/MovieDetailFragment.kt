package com.example.movieapp.fragments.moviedetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Toast
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
import com.example.movieapp.databinding.BottomSheetBuyMovieBinding
import com.example.movieapp.databinding.BottomSheetRentMovieBinding
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.fragments.moviedetail.adapter.CastListAdapter
import com.example.movieapp.remote.api.MovieDBClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.squareup.picasso.Picasso
import org.json.JSONObject
import kotlin.random.Random

class MovieDetailFragment : Fragment(), PaymentResultListener {

    private lateinit var movieDetailBinding: FragmentMovieDetailBinding
    private lateinit var buyMovieBinding: BottomSheetBuyMovieBinding
    private lateinit var rentMovieBinding: BottomSheetRentMovieBinding
    private lateinit var dbHandler: DatabaseHandler
    private lateinit var checkout : Checkout
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

        checkout = Checkout()
        checkout.setKeyID("rzp_test_ElTnwFZKSUpd46")

        fetchUserLists()

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

        movieDetailBinding.buyButton.setOnClickListener {
            showBuyBottomSheet(movieId)
        }

        movieDetailBinding.rentButton.setOnClickListener {
            showRentBottomSheet(movieId)
        }

        /*movieDetailBinding.buyButton.setOnClickListener{
            findNavController().navigate(MovieDetailFragmentDirections.)
        }
        movieDetailBinding.rentButton.setOnClickListener{
            findNavController().navigate(MovieDetailFragmentDirections.)
        }*/
    }

    @SuppressLint("SetTextI18n")
    private fun showBuyBottomSheet(movieId: Int?) {

        buyMovieBinding = BottomSheetBuyMovieBinding.inflate(layoutInflater)
        showBottomNavGraph()

        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(buyMovieBinding.root)

        val randomBuyPrice = Random.nextInt(1000, 2000)
        buyMovieBinding.moviePrice.text = "Price: ₹$randomBuyPrice"

        buyMovieBinding.confirmBuyButton.setOnClickListener {
            startPayment(randomBuyPrice)
        }

        movieDetailViewModel.updateMovieId(movieId ?: 0)
        movieDetailViewModel.movieDetail.observe(viewLifecycleOwner) { moviedetails ->
            Picasso.get().load(MovieDBClient.POSTER_BASE_URL + moviedetails.posterPath)
                .placeholder(R.drawable.poster_placeholder)
                .noFade()
                .into(buyMovieBinding.movieImage)
            buyMovieBinding.movieName.text = moviedetails.title
            buyMovieBinding.closeBuySheetButton.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.show()
        }
    }

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    private fun showRentBottomSheet(movieId: Int?) {

        rentMovieBinding = BottomSheetRentMovieBinding.inflate(layoutInflater)
        showBottomNavGraph()

        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(rentMovieBinding.root)

        val randomRentPrice = Random.nextInt(200, 500)
        rentMovieBinding.moviePrice.text = "Price: ₹$randomRentPrice"

        rentMovieBinding.confirmRentButton.setOnClickListener {
            startPayment(randomRentPrice)
        }

        movieDetailViewModel.updateMovieId(movieId ?: 0)
        movieDetailViewModel.movieDetail.observe(viewLifecycleOwner) { moviedetails ->
            Picasso.get().load(MovieDBClient.POSTER_BASE_URL + moviedetails.posterPath)
                .placeholder(R.drawable.poster_placeholder)
                .noFade()
                .into(rentMovieBinding.movieImage)
            rentMovieBinding.movieName.text = moviedetails.title
            rentMovieBinding.closeRentSheetButton.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.show()
        }
    }

    private fun startPayment(amount: Int) {
        val activity = requireActivity()
        val options = JSONObject()

        try {
            options.put("name", "Movie App")
            options.put("description", "Movie Purchase")
            options.put("theme.color", "#3399cc")
            options.put("image", "https://your_logo_url")
            options.put("currency", "INR")
            options.put("amount", amount * 100)
            options.put("prefill.email", movieDetailViewModel.email)
            options.put("prefill.contact", movieDetailViewModel.phone)
            checkout.open(activity, options)
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun fetchUserLists() {
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
            val messageView = LayoutInflater.from(requireContext()).inflate(R.layout.item_no_lists, null)
            dialogBuilder.setView(messageView)
        } else {
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
/*
        fetchUserLists()
*/
        addToListFab.setOnLongClickListener {
            TooltipCompat.setTooltipText(addToListFab, getString(R.string.add_movie_to_list))
            true
        }

        addToListFab.setOnClickListener {
            showAddToListDialog(lists, movieId)
        }
    }

    private fun showBottomNavGraph() {
        val bottomNavGraph = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavGraph?.visibility = View.VISIBLE
    }

    override fun onPaymentSuccess(razorpayPaymentId: String?) {
        Toast.makeText(requireContext(), "Payment Successful: $razorpayPaymentId", Toast.LENGTH_LONG).show()
    }

    override fun onPaymentError(code: Int, response: String?) {
        Toast.makeText(requireContext(), "Payment Failed: $response", Toast.LENGTH_LONG).show()
    }

}
























