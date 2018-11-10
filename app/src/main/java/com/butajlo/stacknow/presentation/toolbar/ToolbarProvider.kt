package com.butajlo.stacknow.presentation.toolbar

import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController

class ToolbarProvider(
    var toolbar: Toolbar? = null,
    var navController: NavController? = null
) {
    fun back() {
        navController?.navigateUp()
    }
}