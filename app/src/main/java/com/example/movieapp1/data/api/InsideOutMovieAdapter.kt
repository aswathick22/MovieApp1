package com.example.movieapp1.data.api

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.movieapp1.data.movie.InsideOutMovieDetails

class InsideOutMovieAdapter(private val mList: List<InsideOutMovieDetails>) : RecyclerView.Adapter<InsideOutMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return mList.size
    }


    class ViewHolder {

    }


}