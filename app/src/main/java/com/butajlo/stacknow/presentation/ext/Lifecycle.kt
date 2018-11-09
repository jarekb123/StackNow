package com.butajlo.stacknow.presentation.ext

import androidx.lifecycle.Observer

fun <T> ((T) -> Unit).asObserver() = Observer<T> { this(it) }