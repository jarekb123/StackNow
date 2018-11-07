package com.butajlo.stacknow.domain.entity

import java.util.*

data class QuestionEntity(
    val tags: List<String>,
    val owner: UserEntity,
    val questionId: Int,
    val title: String,
    val questionUrl: String,
    val isAnswered: Boolean,
    val lastActivityDate: Calendar,
    val creationDate: Calendar
)