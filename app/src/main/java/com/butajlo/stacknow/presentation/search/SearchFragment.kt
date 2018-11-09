package com.butajlo.stacknow.presentation.search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.butajlo.stacknow.R
import com.butajlo.stacknow.presentation.base.BaseFragment
import com.butajlo.stacknow.presentation.search.results.SearchResultsAdapter
import kotlinx.android.synthetic.main.item_question.*

class SearchFragment : BaseFragment() {

    override val layoutRes = R.layout.search_screen

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_question_tags.apply {
            adapter = SearchResultsAdapter()
            layoutManager = LinearLayoutManager(context)
        }
    }

}