package com.example.movieapp.addedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.addedlist.adapter.AddListAdapter
import com.example.movieapp.database.roomdatabase.data.UserList
import com.example.movieapp.database.roomdatabase.data.UserListRepository
import com.example.movieapp.database.roomdatabase.data.UserListViewModel
import com.example.movieapp.database.roomdatabase.data.ViewModelFactory
import com.example.movieapp.databinding.FragmentAddedListBinding

class AddedListFragment : Fragment() {

    /*private val addedListViewModel by viewModels<UserListViewModel>()*/
    private val addedListViewModel: UserListViewModel by viewModels {
        ViewModelFactory(UserListRepository(requireContext()))
    }
    private var addedListBinding: FragmentAddedListBinding? = null
    private lateinit var adapter: AddListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupRecyclerView()
        setupAddButton()
        addedListBinding = FragmentAddedListBinding.inflate(inflater, container, false)
        return addedListBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addedListViewModel.lists.observe(viewLifecycleOwner) { lists ->
            adapter.submitList(lists)
        }

        addedListViewModel.fetchUser("")
    }

    private fun setupRecyclerView() {
        adapter = AddListAdapter { listId ->
            findNavController().navigate(
                AddedListFragmentDirections.actionAddedListFragmentToMovieDetailFragment(
                    listId
                )
            )
        }
        addedListBinding?.listRecyclerview?.layoutManager = LinearLayoutManager(requireContext())
        addedListBinding?.listRecyclerview?.adapter = adapter
    }

    private fun setupAddButton() {
        addedListBinding?.addActionButton?.setOnClickListener {
            // Logic to show a dialog or navigate to a new fragment to add a list
            // For simplicity, adding a hardcoded list here
            addedListViewModel.addList(UserList(0, "New List", 1)) // Replace 1 with actual userId
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        addedListBinding = null
    }

}
    /*addedListViewModel..observe(viewLifecycleOwner) {items ->
        addedListBinding.listRecyclerview.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = AddedListAdapter(items.results)
        }

    }*/

/*@AndroidEntryPoint
class AddListFragment : Fragment() {

    private late init var viewModel: UserListViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Injection.provideListRepository(requireContext())
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserListViewModel::class.java)

        // Setup RecyclerView and other UI elements here

        // Example usage:
        val userId = 1 // Replace with actual user ID from SQLiteHelper
        viewModel.fetchLists(userId)
        viewModel.lists.observe(viewLifecycleOwner) {
            // Update UI with the list of movies
        }

        val listId = 1 // Replace with the actual list ID
        viewModel.fetchMoviesForList(listId)
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            // Update UI with the list of movies in the list
        }

        // Example button to add a movie to a list
        view.findViewById<Button>(R.id.addMovieButton).setOnClickListener {
            val movieId = 123 // Replace with the actual movie ID
            viewModel.addMovieToList(listId, movieId)
        }
    }
}*/


