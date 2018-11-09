package com.butajlo.stacknow.presentation.search.results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.butajlo.stacknow.R
import com.butajlo.stacknow.databinding.ItemQuestionBinding
import com.butajlo.stacknow.presentation.ext.binding
import kotlinx.android.synthetic.main.item_question.view.*

class SearchResultsAdapter : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    private val results = mutableListOf<QuestionBinding>()

    override fun getItemCount() = results.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(results[position])
    }

    fun setResults(searchResults: List<QuestionBinding>) {
        results.clear()
        results.addAll(searchResults)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: ItemQuestionBinding? by binding()

        fun bind(question: QuestionBinding) {
            binding?.question = question
        }

    }
}