package com.butajlo.stacknow.presentation.network

import com.butajlo.stacknow.data.service.SearchService
import org.koin.dsl.module.module
import retrofit2.Retrofit

val serviceModule = module {
    single { createSearchService(get()) }
}

private fun createSearchService(retrofit: Retrofit) = retrofit.create(SearchService::class.java)