package com.example.movieapp.addedlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.database.roomdatabase.data.UserList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.movieapp.databinding.ItemAddListBinding

class AddListAdapter(private val onClick: (Int) -> Unit) : ListAdapter<UserList, AddListAdapter.AddListViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<UserList>() {
        override fun areItemsTheSame(oldItem: UserList, newItem: UserList): Boolean {
            return oldItem.listId == newItem.listId
        }

        override fun areContentsTheSame(oldItem: UserList, newItem: UserList): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddListViewHolder {
        val binding = ItemAddListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddListViewHolder, position: Int) {
        val userList = getItem(position)
        holder.bind(userList, onClick)
    }

    class AddListViewHolder(private val binding: ItemAddListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(userList: UserList, onClick: (Int) -> Unit) {
            binding.listName.text = userList.listName
            binding.dropdownIcon.setOnClickListener {
                // Logic to drop down the list of movies
            }
            binding.dropupIcon.setOnClickListener {
                // Logic to collapse the list of movies
            }
            binding.root.setOnClickListener {
                onClick(userList.listId)
            }
        }
    }
}


/*class AddedListAdapter(private val mList: List<ResultXXXX>, *//*private val onMovieItemClick : (ResultXXXX) -> Unit*//*) : RecyclerView.Adapter<AddedListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_add_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        Picasso.get().load(MovieDBClient.POSTER_BASE_URL + itemsViewModel.posterPath)
            .placeholder(R.drawable.poster_placeholder)
            .noFade()
            .into(holder.imageView1)
        *//*holder.itemView.setOnClickListener { onMovieItemClick(itemsViewModel) }*//*
        holder.textView1.text = itemsViewModel.name
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val imageView1 : ImageView = itemView.findViewById(R.id.movie_image)
        val textView1 : TextView = itemView.findViewById(R.id.list_name)
        val floatingActionButton : FloatingActionButton = itemView.findViewById(R.id.add_action_button)
        val dropdownButton : Button = itemView.findViewById(R.id.dropdown_icon)
        val dropupButton : Button = itemView.findViewById(R.id.dropup_icon)
    }

}*/
