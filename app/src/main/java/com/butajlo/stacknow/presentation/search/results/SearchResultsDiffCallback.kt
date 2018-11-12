package com.butajlo.stacknow.presentation.search.results

import androidx.recyclerview.widget.DiffUtil

class SearchResultsDiffCallback(
    private val oldData: List<QuestionBinding>,
    private val newData: List<QuestionBinding>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldData.size

    override fun getNewListSize() = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldData[oldItemPosition].questionUrl == newData[newItemPosition].questionUrl

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldData[oldItemPosition] == newData[newItemPosition]
}