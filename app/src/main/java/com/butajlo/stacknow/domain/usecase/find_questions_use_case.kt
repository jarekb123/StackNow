package com.butajlo.stacknow.domain.usecase

import com.butajlo.stacknow.domain.entity.QuestionEntity
import com.butajlo.stacknow.domain.repository.SearchStackRepository
import io.reactivex.Single

fun findQuestions(
    repository: SearchStackRepository,
    searchString: String,
    page: Int
): Single<List<QuestionEntity>> {
    val tags = searchString.split(" ")

    return repository.findQuestions(inTitle = searchString, tags = tags, page = page)
}