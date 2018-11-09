package com.butajlo.stacknow.presentation.di

import com.butajlo.stacknow.presentation.search.SearchViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
}