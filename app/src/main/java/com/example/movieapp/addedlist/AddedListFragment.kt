package com.example.movieapp.addedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.addedlist.adapter.AddedListAdapter
import com.example.movieapp.databinding.FragmentAddedListBinding

class AddedListFragment : Fragment() {

    private val addedListViewModel by viewModels<AddedListViewModel>()
    private lateinit var addedListBinding: FragmentAddedListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addedListBinding = FragmentAddedListBinding.inflate(inflater, container, false)
        return addedListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addedListViewModel.addedList.observe(viewLifecycleOwner) {items ->
            addedListBinding.retrofitRecyclerview.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = AddedListAdapter(items.results)
            }

        }

    }
}
