package com.butajlo.stacknow.presentation.search.results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.butajlo.stacknow.R
import com.butajlo.stacknow.databinding.ItemQuestionBinding
import com.butajlo.stacknow.presentation.ext.binding
import kotlinx.android.synthetic.main.item_question.view.*


class SearchResultsAdapter(private val onItemClick: (QuestionBinding) -> Unit) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    private val results = mutableListOf<QuestionBinding>()

    override fun getItemCount() = results.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)

        return ViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(results[position])
    }

    fun updateResults(searchResults: List<QuestionBinding>) {
        val diffResult = DiffUtil.calculateDiff(SearchResultsDiffCallback(results, searchResults))
        results.apply {
            clear()
            addAll(searchResults)
        }
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(itemView: View, onItemClick: (QuestionBinding) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val binding: ItemQuestionBinding? by binding()

        init {
            itemView.setOnClickListener { binding?.question?.apply { onItemClick(this) } }
        }

        fun bind(question: QuestionBinding) {
            binding?.question = question
        }

    }
}