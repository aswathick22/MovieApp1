package com.example.movieapp.fragments.castlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.remote.api.MovieDBClient.POSTER_BASE_URL
import com.example.movieapp.remote.data.Cast
import com.squareup.picasso.Picasso

class CastListAdapter(private val mList: List<Cast>, private val onCastItemClick : (Cast) -> Unit) : RecyclerView.Adapter<CastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        Picasso.get().load(POSTER_BASE_URL + itemsViewModel.profilePath)
            .placeholder(R.drawable.poster_placeholder)
            .noFade()
            .into(holder.imageView)
        holder.itemView.setOnClickListener { onCastItemClick(itemsViewModel) }
        holder.textView.text = itemsViewModel.name
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView.findViewById(R.id.cast_image)
        val textView : TextView = itemView.findViewById(R.id.cast_text)
    }

}