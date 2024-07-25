package com.example.movieapp.addedlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.remote.api.MovieDBClient
import com.example.movieapp.remote.data.ResultXXXX
import com.squareup.picasso.Picasso

class AddedListAdapter(private val mList: List<ResultXXXX>, /*private val onMovieItemClick : (ResultXXXX) -> Unit*/) : RecyclerView.Adapter<AddedListAdapter.ViewHolder>() {

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
        /*holder.itemView.setOnClickListener { onMovieItemClick(itemsViewModel) }*/
        holder.textView1.text = itemsViewModel.name
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val imageView1 : ImageView = itemView.findViewById(R.id.movie_image)
        val textView1 : TextView = itemView.findViewById(R.id.list_name)
    }

}
