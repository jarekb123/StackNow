package com.butajlo.stacknow.domain.repository

import com.butajlo.stacknow.domain.entity.QuestionEntity
import io.reactivex.Single

interface SearchStackRepository {

    fun findQuestions(inTitle: String, tags: List<String>, page: Int): Single<List<QuestionEntity>>
}