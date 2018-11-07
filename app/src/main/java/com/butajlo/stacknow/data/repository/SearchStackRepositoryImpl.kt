package com.butajlo.stacknow.data.repository

import com.butajlo.stacknow.data.mapper.toEntity
import com.butajlo.stacknow.data.service.SearchService
import com.butajlo.stacknow.domain.entity.QuestionEntity
import com.butajlo.stacknow.domain.repository.SearchStackRepository
import io.reactivex.Single

class SearchStackRepositoryImpl(private val searchService: SearchService) : SearchStackRepository {

    override fun findQuestions(inTitle: String, tags: List<String>, page: Int): Single<List<QuestionEntity>> =
        searchService.findQuestion(
            inTitle = inTitle,
            tags = tags.joinToString(separator = ";"),
            page = page,
            order = ORDER_TYPE,
            site = STACKOVERFLOW_SITE_NAME
        ).map { it.items.map { it.toEntity() } }

    companion object {
        private const val STACKOVERFLOW_SITE_NAME = "stackoverflow"
        private const val ORDER_TYPE = "desc"
    }
}