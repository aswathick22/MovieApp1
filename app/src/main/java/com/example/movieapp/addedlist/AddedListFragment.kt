package com.example.movieapp.addedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.addedlist.adapter.AddListAdapter
import com.example.movieapp.database.roomdatabase.data.UserList
import com.example.movieapp.database.roomdatabase.data.UserListRepository
import com.example.movieapp.database.roomdatabase.data.UserListViewModel
import com.example.movieapp.database.roomdatabase.data.UserListViewModelFactory
import com.example.movieapp.databinding.FragmentAddedListBinding

class AddedListFragment : Fragment() {

    private val addedListViewModel: UserListViewModel by viewModels {
        UserListViewModelFactory(UserListRepository(requireContext()))
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

        val userId = arguments?.getInt("userId") ?: 1
        val movieId = arguments?.getInt("movieId")

        addedListViewModel.lists.observe(viewLifecycleOwner) { lists ->
            adapter.submitList(lists)
        }

        addedListViewModel.fetchLists(userId)
        if (movieId != null) {
            addedListViewModel.addMovieToList(userId, movieId)
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.movie_list_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.delete_list -> {
                        showDeleteListDialog()
                        true
                    }
                    R.id.clear_list -> {
                        showClearAllListsDialog()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.sort_list_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.sort_az -> {
                        sortListsByNameAscending()
                        true
                    }
                    R.id.sort_za -> {
                        sortListsByNameDescending()
                        true
                    }
                    R.id.sort_date_created -> {
                        sortListsByDateCreated()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    private fun sortListsByNameAscending() {
        userListViewModel.lists.value?.let { lists ->
            adapter.submitList(lists.sortedBy { it.listName })
        }
    }

    private fun sortListsByNameDescending() {
        userListViewModel.lists.value?.let { lists ->
            adapter.submitList(lists.sortedByDescending { it.listName })
        }
    }

    private fun sortListsByDateCreated() {
        userListViewModel.lists.value?.let { lists ->
            adapter.submitList(lists.sortedBy { it.listId })
        }
    }

    private fun setupRecyclerView() {
        adapter = AddListAdapter {
            val userId = arguments?.getInt("userId") ?: 1
            val movieId = arguments?.getInt("movieId")

            if(movieId != null) {
                findNavController().navigate(AddedListFragmentDirections.actionAddedListFragmentToMovieDetailFragment(userId, movieId)) }
        }
        addedListBinding?.listRecyclerview?.layoutManager = LinearLayoutManager(requireContext())
        addedListBinding?.listRecyclerview?.adapter = adapter
    }

    private fun setupAddButton() {
        addedListBinding?.addActionButton?.setOnClickListener {
            showCreateListDialog()
        }
    }

    private fun showDeleteListDialog() {
        val lists = addedListViewModel.lists.value ?: return
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("Delete Lists")

        if (lists.isEmpty()) {
            dialogBuilder.setMessage("No lists available to delete.")
            dialogBuilder.setPositiveButton("OK", null)
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
            dialogBuilder.setPositiveButton("Delete") { _, _ ->
                lists.forEachIndexed { index, list ->
                    if (checkedItems[index]) {
                        addedListViewModel.deleteList(list.listId)
                    }
                }
            }
            dialogBuilder.setNegativeButton("Cancel", null)
        }

        dialogBuilder.create().show()
    }

    private fun showClearAllListsDialog() {
        val userId = arguments?.getInt("userId") ?: 1
        AlertDialog.Builder(requireContext())
            .setTitle("Clear All Lists")
            .setMessage("Are you sure you want to delete all lists?")
            .setPositiveButton("Yes") { _, _ ->
                addedListViewModel.clearAllLists(userId)
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }


    private fun showCreateListDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Create New List")
        val userId = arguments?.getInt("userId") ?: 1
        val input = EditText(requireContext())
        builder.setView(input)

        builder.setPositiveButton("Create") { _, _ ->
            val listName = input.text.toString().trim()
            if (listName.isEmpty()) {
                Toast.makeText(requireContext(), "List name cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                userListViewModel.addListForUser(UserList(0, userId, listName))
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
                val newUserList = UserList(0, 0, listName)
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




