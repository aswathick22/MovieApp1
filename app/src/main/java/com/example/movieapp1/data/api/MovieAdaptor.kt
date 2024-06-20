package com.example.movieapp1.data.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp1.data.movie.MovieId
import com.example.movieapp1.R

abstract class MovieAdaptor(private val mList: List<MovieId>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val itemsViewModel = mList[position]
            holder.textView1.text = itemsViewModel.id
            holder.textView2.text = itemsViewModel.firstName
            holder.textView3.text = itemsViewModel.lastName
        }

        override fun getItemCount(): Int {
            return mList.size
        }

        class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            val textView1 : TextView = itemView.findViewById(R.id.movie_id)
            val textView2 : TextView = itemView.findViewById(R.id.movie_title)
            val textView3 : TextView = itemView.findViewById(R.id.movie_genre)
        }

    }


}
