package com.butajlo.stacknow.presentation.search.results

import com.butajlo.stacknow.domain.entity.QuestionEntity
import java.text.SimpleDateFormat
import java.util.*

data class QuestionBinding(
    val title: String,
    val username: String,
    val date: String,
    val tags: List<String>
)

fun QuestionEntity.toBinding() = QuestionBinding(
    title = title,
    username = owner.username,
    date = let {
        val format = SimpleDateFormat("MMMM d, yyyy h:mm a", Locale.US)
        format.format(creationDate.time)
    },
    tags = tags
)