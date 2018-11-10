package com.butajlo.stacknow.presentation.search

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.butajlo.stacknow.R
import com.butajlo.stacknow.presentation.base.BaseFragment
import com.butajlo.stacknow.presentation.details.DetailsFragment.Companion.ARGUMENT_DETAILS_URL
import com.butajlo.stacknow.presentation.ext.argument
import com.butajlo.stacknow.presentation.ext.asObserver
import com.butajlo.stacknow.presentation.search.results.QuestionBinding
import com.butajlo.stacknow.presentation.search.results.SearchResultsAdapter
import com.butajlo.stacknow.presentation.toolbar.ToolbarProvider
import kotlinx.android.synthetic.main.search_screen.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {

    override val layoutRes = R.layout.search_screen

    private val toolbarProvider by inject<ToolbarProvider>()
    private val searchViewModel by viewModel<SearchViewModel>()
    private val searchQuery by argument<String>(ARGUMENT_SEARCH_QUERY)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            searchQuery?.run {
                searchViewModel.searchQuestions(this)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarProvider.toolbar?.navigationIcon = null
        searchViewModel.getSearchResult().observe(this, ::updateSearchResults.asObserver())

        rv_search_results.apply {
            adapter = SearchResultsAdapter(onItemClick = ::showDetails)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun updateSearchResults(questions: List<QuestionBinding>) {
        (rv_search_results.adapter as SearchResultsAdapter).setResults(questions)
    }

    private fun showDetails(question: QuestionBinding) {
        findNavController().navigate(
            R.id.action_searchFragment_to_detailsFragment,
            bundleOf(ARGUMENT_DETAILS_URL to question.questionUrl)
        )
    }

    companion object {
        const val ARGUMENT_SEARCH_QUERY = "ARGUMENT_SEARCH_QUERY"
    }

}