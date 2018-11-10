package com.butajlo.stacknow.presentation.toolbar

import com.butajlo.stacknow.presentation.di.ACTIVITY_SCOPE
import org.koin.dsl.module.module

val toolbarModule = module {
    scope(ACTIVITY_SCOPE) { ToolbarProvider() }
}