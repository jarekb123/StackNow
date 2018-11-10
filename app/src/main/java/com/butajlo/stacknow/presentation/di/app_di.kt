package com.butajlo.stacknow.presentation.di

import com.butajlo.stacknow.presentation.network.networkModule
import com.butajlo.stacknow.presentation.network.serviceModule
import com.butajlo.stacknow.presentation.toolbar.toolbarModule

val appModules = listOf(
    networkModule,
    serviceModule,
    viewModelModule,
    toolbarModule
)
