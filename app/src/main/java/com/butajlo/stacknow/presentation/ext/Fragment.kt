package com.butajlo.stacknow.presentation.ext

import androidx.fragment.app.Fragment

inline fun <reified T : Any> Fragment.argument(key: String) = lazy { arguments?.get(key) as T? }