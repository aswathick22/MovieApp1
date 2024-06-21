package com.example.movieapp1.data.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp1.R
import com.example.movieapp1.data.api.MovieDBClient.POSTER_BASE_URL
import com.example.movieapp1.data.movie.MovieItem
import com.squareup.picasso.Picasso

class MovieAdapter(private val mList: List<MovieItem>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        Picasso.get().load(POSTER_BASE_URL + itemsViewModel.posterPath).into(holder.imageView1);
        holder.textView1.text = itemsViewModel.title
        holder.textView2.text = itemsViewModel.releaseDate
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val imageView1 : ImageView = itemView.findViewById(R.id.movie_image)
        val textView1 : TextView = itemView.findViewById(R.id.movie_title)
        val textView2 : TextView = itemView.findViewById(R.id.movie_release_date)
    }

}