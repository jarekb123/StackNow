package com.butajlo.stacknow.presentation.network

import com.butajlo.stacknow.data.repository.SearchStackRepositoryImpl
import com.butajlo.stacknow.data.service.SearchService
import com.butajlo.stacknow.domain.repository.SearchStackRepository
import org.koin.dsl.module.module
import retrofit2.Retrofit

val serviceModule = module {
    single { createSearchService(get()) }
    single { SearchStackRepositoryImpl(get()) as SearchStackRepository }
}

private fun createSearchService(retrofit: Retrofit) = retrofit.create(SearchService::class.java)