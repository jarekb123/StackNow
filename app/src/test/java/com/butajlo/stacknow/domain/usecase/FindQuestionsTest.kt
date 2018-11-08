package com.butajlo.stacknow.domain.usecase

import com.butajlo.stacknow.data.mapper.toEntity
import com.butajlo.stacknow.data.model.SearchResponseData
import com.butajlo.stacknow.domain.repository.SearchStackRepository
import com.butajlo.stacknow.testutils.parseJson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import java.util.*

class FindQuestionsTest {

    private val questions =
        parseJson<SearchResponseData>("mock/get_search.json").items.map { it.toEntity() }

    private val searchStackRepository = mock<SearchStackRepository> {
        on(it.findQuestions(anyString(), anyList(), anyInt()))
            .thenReturn(Single.just(questions))
    }


    @Test
    fun findQuestions_CheckSize_ShouldReturnQuestionItemsSize() {
        findQuestions(
            repository = searchStackRepository,
            searchString = anyString(),
            page = anyInt()
        )
            .test()
            .assertValue { it.size == questions.size }
    }

    @Test
    fun findQuestions_CheckReturnedValues() {
        findQuestions(
            repository = searchStackRepository,
            searchString = anyString(),
            page = anyInt()
        )
            .test()
            .assertValue { it[1].title == "no borders in apple but in android" }
            .assertValue { it[2].creationDate.get(Calendar.YEAR) == 2016 }
    }

    @Test
    fun findQuestions_RepositoryReturnEmptyList_ShouldReturnEmptyList() {
        whenever(searchStackRepository.findQuestions(anyString(), anyList(), anyInt()))
            .thenReturn(Single.just(emptyList()))
        findQuestions(repository = searchStackRepository, searchString = "bad words", page = 1)
            .test()
            .assertValue { it.isEmpty() }
    }
}