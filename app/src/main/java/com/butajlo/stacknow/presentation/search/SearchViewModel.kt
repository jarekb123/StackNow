package com.butajlo.stacknow.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.butajlo.stacknow.domain.repository.SearchStackRepository
import com.butajlo.stacknow.domain.usecase.findQuestions
import com.butajlo.stacknow.presentation.search.results.QuestionBinding
import com.butajlo.stacknow.presentation.search.results.toBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchViewModel(private val repository: SearchStackRepository) : ViewModel() {

    private val subscriptions = CompositeDisposable()
    private val searchResult = MutableLiveData<List<QuestionBinding>>()

    fun getSearchResult(): LiveData<List<QuestionBinding>> = searchResult

    fun searchQuestions(searchQuery: String, page: Int = 1) {
        findQuestions(
            repository = repository,
            searchString = searchQuery,
            page = page
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { searchResult.value = it.map { it.toBinding() } },
                onError = { Timber.e(it) }
            ).addTo(subscriptions)
    }

    override fun onCleared() {
        subscriptions.clear()
    }
}