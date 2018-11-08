package com.butajlo.stacknow.domain.usecase

import com.butajlo.stacknow.data.mapper.toEntity
import com.butajlo.stacknow.data.model.SearchResponseData
import com.butajlo.stacknow.domain.repository.SearchStackRepository
import com.butajlo.stacknow.testutils.parseJson
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test
import java.util.*

class FindQuestionsTest {

    private val questions =
        parseJson<SearchResponseData>("mock/get_search.json").items.map { it.toEntity() }

    private val searchStackRepository = mock<SearchStackRepository> {
        on(it.findQuestions("word word2", listOf("word", "word2"), 1))
            .thenReturn(Single.just(questions))
    }


    @Test
    fun findQuestions_CheckSize_ShouldReturnQuestionItemsSize() {
        findQuestions(repository = searchStackRepository, searchString = "word word2", page = 1)
            .test()
            .assertValue { it.size == questions.size }
    }

    @Test
    fun findQuestions_CheckReturnedValues() {
        findQuestions(repository = searchStackRepository, searchString = "word word2", page = 1)
            .test()
            .assertValue { it[1].title == "no borders in apple but in android" }
            .assertValue { it[2].creationDate.get(Calendar.YEAR) == 2016 }
    }

    @Test
    fun findQuestions_BadSearchString_ShouldReturnEmptyList() {
        findQuestions(repository = searchStackRepository, searchString = "bad words", page = 1)
            .test()
            .assertValue { it.isEmpty() }
    }
}