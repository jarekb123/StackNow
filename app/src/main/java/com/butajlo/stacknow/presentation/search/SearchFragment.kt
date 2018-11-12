package com.butajlo.stacknow.presentation.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.butajlo.stacknow.R
import com.butajlo.stacknow.presentation.base.BaseFragment
import com.butajlo.stacknow.presentation.details.DetailsFragment.Companion.ARGUMENT_DETAILS_URL
import com.butajlo.stacknow.presentation.ext.argument
import com.butajlo.stacknow.presentation.ext.asObserver
import com.butajlo.stacknow.presentation.search.results.QuestionBinding
import com.butajlo.stacknow.presentation.search.results.SearchResultsAdapter
import com.butajlo.stacknow.presentation.service.LastQueryWorker
import com.butajlo.stacknow.presentation.toolbar.ToolbarProvider
import kotlinx.android.synthetic.main.screen_search.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class SearchFragment : BaseFragment() {

    override val layoutRes = R.layout.screen_search

    private val toolbarProvider by inject<ToolbarProvider>()
    private val searchViewModel by viewModel<SearchViewModel>()
    private val searchQuery by argument<String>(ARGUMENT_SEARCH_QUERY)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (savedInstanceState == null) {
            searchQuestions()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarProvider.toolbar?.navigationIcon = null
        searchViewModel.also {
            it.isLoading().observe(this, ::updateLoadingStatus.asObserver())
            it.getSearchResult().observe(this, ::updateSearchResults.asObserver())
        }
        swipe_refresh_results.isEnabled = false

        rv_search_results.apply {
            adapter = SearchResultsAdapter(onItemClick = ::showDetails)
            layoutManager = LinearLayoutManager(context)
        }
        swipe_refresh_results.apply {
            setOnRefreshListener(::searchQuestions)
            isEnabled = true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        (menu.findItem(R.id.menu_search).actionView as SearchView).also {
            it.maxWidth = Int.MAX_VALUE
            it.setIconifiedByDefault(true)
            it.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    startLastQueryWorker(query)
                    findNavController()
                        .navigate(
                            R.id.action_searchFragment_self,
                            bundleOf(SearchFragment.ARGUMENT_SEARCH_QUERY to query)
                        )
                    it.clearFocus()
                    return false
                }

                override fun onQueryTextChange(newText: String?) = false
            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun startLastQueryWorker(query: String?) {
        val data = Data.Builder()
            .putString(LastQueryWorker.EXTRA_QUERY, query)
            .build()
        val workConstraints =
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val workRequest =
            PeriodicWorkRequest
                .Builder(LastQueryWorker::class.java, PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
                .setConstraints(workConstraints)
                .setInputData(data)
                .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            LastQueryWorker.WORK_LAST_QUERY,
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }

    private fun searchQuestions() {
        searchQuery?.run {
            searchViewModel.searchQuestions(this)
        }
    }

    private fun updateLoadingStatus(isLoading: Boolean) {
        swipe_refresh_results.isRefreshing = isLoading
    }

    private fun updateSearchResults(questions: List<QuestionBinding>) {
        (rv_search_results.adapter as SearchResultsAdapter).updateResults(questions)
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