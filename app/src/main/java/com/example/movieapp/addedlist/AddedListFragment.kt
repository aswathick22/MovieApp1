package com.example.movieapp.addedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.addedlist.adapter.AddListAdapter
import com.example.movieapp.database.roomdatabase.data.UserList
import com.example.movieapp.database.roomdatabase.data.UserListRepository
import com.example.movieapp.database.roomdatabase.data.UserListViewModel
import com.example.movieapp.database.roomdatabase.data.UserListViewModelFactory
import com.example.movieapp.database.roomdatabase.data.ViewModelFactory
import com.example.movieapp.databinding.FragmentAddedListBinding

class AddedListFragment : Fragment() {

    private val addedListViewModel: UserListViewModel by viewModels {
        ViewModelFactory(UserListRepository(requireContext()))
    }
    private var addedListBinding: FragmentAddedListBinding? = null
    private val userListViewModel: UserListViewModel by viewModels {
        UserListViewModelFactory(UserListRepository(requireContext()))
    }

    private lateinit var adapter: AddListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        addedListBinding = FragmentAddedListBinding.inflate(inflater, container, false)
        return addedListBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupAddButton()

        addedListViewModel.lists.observe(viewLifecycleOwner) { lists ->
            adapter.submitList(lists)
        }

        addedListViewModel.fetchLists("")
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
            showCreateListDialog()
        }
    }

    private fun showCreateListDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Create New List")

        val input = EditText(requireContext())
        builder.setView(input)

        builder.setPositiveButton("Create") { _, _ ->
            val listName = input.text.toString().trim()
            if (listName.isEmpty()) {
                Toast.makeText(requireContext(), "List name cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                userListViewModel.addListForUser(UserList(0, "userId", listName))
                Toast.makeText(requireContext(), "List '$listName' created", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun showAddListDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Add New List")

        val input = EditText(requireContext())
        input.hint = "List Name"
        builder.setView(input)

        builder.setPositiveButton("Add") { _, _ ->
            val listName = input.text.toString().trim()
            if (listName.isNotEmpty()) {
                val newUserList = UserList(0, "", listName)
                userListViewModel.addListForUser(newUserList)
            }
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        addedListBinding = null
    }

}




