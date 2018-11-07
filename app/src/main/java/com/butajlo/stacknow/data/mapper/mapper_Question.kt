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
        creationDate = (creationDate * 1000L).toCalendar(),
        lastActivityDate = (lastActivityDate * 1000L).toCalendar(),
        isAnswered = isAnswered
    )