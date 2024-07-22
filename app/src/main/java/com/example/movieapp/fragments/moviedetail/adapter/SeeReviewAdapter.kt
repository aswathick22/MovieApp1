package com.example.movieapp.fragments.moviedetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.remote.data.ResultXX

class SeeReviewAdapter(private var mList: List<ResultXX>,
                       private val onShowMoreClick : (TextView, ResultXX) -> Unit,
                       private val onShowLessClick : (TextView, ResultXX) -> Unit)
    : RecyclerView.Adapter<SeeReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_see_reviews, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder.textView1.text = item.author
        holder.textView2.text = item.content
        holder.bind(item, onShowMoreClick, onShowLessClick)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateData(newList: List<ResultXX>){
        mList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.author_name)
        val textView2: TextView = itemView.findViewById(R.id.review_content_text)
        private val showMoreTextView: TextView = itemView.findViewById(R.id.show_more_text)
        private val showLessTextView: TextView = itemView.findViewById(R.id.show_less_text)

        private var isExpanded = false

        fun bind(
            item: ResultXX,
            onShowMoreClick: (TextView, ResultXX) -> Unit,
            onShowLessClick: (TextView, ResultXX) -> Unit
        ) {
            /*textView2.maxLines = if (isExpanded) Int.MAX_VALUE else 3
            showMoreTextView.visibility = if (isExpanded) View.GONE else View.VISIBLE
            showLessTextView.visibility = if (isExpanded) View.VISIBLE else View.GONE*/
            textView2.maxLines = 3

            // Check if the text needs more lines
            textView2.post {
                if (textView2.lineCount > 3) {
                    showMoreTextView.visibility = View.VISIBLE
                    showLessTextView.visibility = View.GONE
                } else {
                    showMoreTextView.visibility = View.GONE
                    showLessTextView.visibility = View.GONE
                }
            }

            showMoreTextView.setOnClickListener {
                onShowMoreClick(textView2, item)
                isExpanded = true
                textView2.maxLines = Int.MAX_VALUE
                showMoreTextView.visibility = View.GONE
                showLessTextView.visibility = View.VISIBLE
            }

            showLessTextView.setOnClickListener {
                onShowLessClick(textView2, item)
                isExpanded = false
                textView2.maxLines = 3
                showMoreTextView.visibility = View.VISIBLE
                showLessTextView.visibility = View.GONE
            }
        }
    }

}

