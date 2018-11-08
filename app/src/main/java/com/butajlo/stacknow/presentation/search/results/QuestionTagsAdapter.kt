package com.butajlo.stacknow.presentation.search.results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.butajlo.stacknow.R
import kotlinx.android.synthetic.main.item_question_tag.view.*

class QuestionTagsAdapter : RecyclerView.Adapter<QuestionTagsAdapter.ViewHolder>() {

    private val tags = mutableListOf<String>()

    override fun getItemCount() = tags.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question_tag, null, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_question_tag.text = tags[position]
    }

    fun setTags(data: List<String>) {
        tags.clear()
        tags.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

@BindingAdapter("tags")
fun bindTagsToRecyclerView(recyclerView: RecyclerView, tags: List<String>) {
    recyclerView.adapter?.apply {
        if (this is QuestionTagsAdapter) {
            setTags(tags)
        }
    }
}