package com.butajlo.stacknow.data.mapper

import com.butajlo.stacknow.data.model.QuestionData
import com.butajlo.stacknow.domain.entity.QuestionEntity

fun QuestionData.toEntity() =
    QuestionEntity(
        tags = tags,
        owner = owner.toEntity(),
        questionId = questionId,
        title = title,
        questionUrl = link,
        creationDate = creationDate.toCalendar(),
        lastActivityDate = lastActivityDate.toCalendar(),
        isAnswered = isAnswered
    )