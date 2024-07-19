package com.example.movieapp.fragments.moviedetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.remote.data.PopularMovieDetails
import com.example.movieapp.remote.data.ResultXX

class SeeReviewAdapter(private val mList: List<ResultXX>) : RecyclerView.Adapter<SeeReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_see_reviews, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        holder.textView1.text = itemsViewModel.author
        holder.textView2.text = itemsViewModel.content
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.author_name)
        val textView2: TextView = itemView.findViewById(R.id.review_content_text)
    }

}

