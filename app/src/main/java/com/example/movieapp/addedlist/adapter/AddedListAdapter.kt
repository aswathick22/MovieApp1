package com.example.movieapp.addedlist.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.database.roomdatabase.data.UserList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.movieapp.databinding.ItemAddListBinding

class AddListAdapter(private val onClick: (Int) -> Unit) : ListAdapter<UserList, AddListAdapter.AddListViewHolder>(DiffCallback) {

    private val expandedState = SparseBooleanArray()

    companion object DiffCallback : DiffUtil.ItemCallback<UserList>() {
        override fun areItemsTheSame(oldItem: UserList, newItem: UserList): Boolean {
            return oldItem.listId == newItem.listId
        }

        override fun areContentsTheSame(oldItem: UserList, newItem: UserList): Boolean {
            return oldItem == newItem
        }
    }

    inner class AddListViewHolder(private val binding: ItemAddListBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var adapter: AddListAdapter

        fun bind(userList: UserList, isExpanded: Boolean) {
            binding.listName.text = userList.listName

            binding.movieRecyclerview.visibility = if (isExpanded) View.VISIBLE else View.GONE

            binding.dropdownIcon.setOnClickListener {
                binding.movieRecyclerview.visibility = View.VISIBLE
                val isCurrentlyExpanded = expandedState[adapterPosition, false]
                expandedState.put(adapterPosition, !isCurrentlyExpanded)
                /*binding.dropdownIcon.setImageResource(R.drawable.dropdown_icon)*/
                adapter.notifyItemChanged(adapterPosition)
            }

            binding.dropupIcon.setOnClickListener {
                binding.movieRecyclerview.visibility = View.GONE
                adapter.notifyItemChanged(adapterPosition)
            }

            binding.root.setOnClickListener {
                onClick(userList.listId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddListViewHolder {
        val binding = ItemAddListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddListViewHolder, position: Int) {
        val userList = getItem(position)
        val isExpanded = expandedState[position, false]
        holder.bind(userList, isExpanded)
    }

}



