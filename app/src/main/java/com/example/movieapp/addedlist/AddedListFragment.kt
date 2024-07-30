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
            addedListViewModel.addList(UserList(0, "", "")) // Replace 1 with actual userId
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



