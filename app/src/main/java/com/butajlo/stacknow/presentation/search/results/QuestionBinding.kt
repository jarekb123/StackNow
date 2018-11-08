package com.butajlo.stacknow.presentation.search.results

import com.butajlo.stacknow.domain.entity.QuestionEntity
import java.text.SimpleDateFormat

data class QuestionBinding(
    val title: String,
    val username: String,
    val date: String,
    val tags: List<String>
)

fun QuestionEntity.toBinding() = QuestionBinding(
    title = title,
    username = owner.username,
    date = creationDate.toString(),
    tags = tags
)