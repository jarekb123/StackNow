package com.butajlo.stacknow.presentation.di

import android.app.SearchManager
import android.content.Context
import com.butajlo.stacknow.presentation.network.networkModule
import com.butajlo.stacknow.presentation.network.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    single { androidContext().getSystemService(Context.SEARCH_SERVICE) as SearchManager }
}

val appModules = listOf(
    appModule,
    networkModule,
    serviceModule,
    viewModelModule
)
