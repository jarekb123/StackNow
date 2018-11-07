package com.butajlo.stacknow.presentation.base

import com.butajlo.stacknow.presentation.network.networkModule
import com.butajlo.stacknow.presentation.network.serviceModule

val appModules = listOf(
    networkModule,
    serviceModule
)