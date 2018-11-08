package com.butajlo.stacknow.presentation.search.results

data class QuestionBinding(
    val title: String,
    val username: String,
    val date: String,
    val tags: List<String>
)