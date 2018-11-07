package com.butajlo.stacknow.data.repository

import com.butajlo.stacknow.data.model.SearchResponseData
import com.butajlo.stacknow.data.service.SearchService
import com.butajlo.stacknow.testutils.parseJson
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString
import java.util.*

class SearchStackRepositoryImplTest {

    private val responseData = parseJson<SearchResponseData>("mock/get_search.json")
    private val searchService = mock<SearchService> {
        on(
            it.findQuestion(
                "title",
                "",
                1,
                "desc",
                "stackoverflow"
            )
        ).thenReturn(Single.just(responseData))
    }
    private val repository = SearchStackRepositoryImpl(searchService)

    @Test
    fun findQuestions_CheckSize_ShouldReturnResponseDataItemsSize() {
        repository.findQuestions("title", emptyList(), 1)
            .test()
            .assertValue { it.size == responseData.items.size }
    }

    @Test
    fun findQuestions_CheckReturnedValues() {
        repository.findQuestions("title", emptyList(), 1)
            .test()
            .assertValue {
                it[0].title == "Apple Watch AppIcon for Long-Look notification is missing in Assets.xcassets"
            }
            .assertValue { !it[0].isAnswered }
            .assertValue { it[0].lastActivityDate.get(Calendar.DAY_OF_MONTH) == 7 }
            .assertValue { it[0].creationDate.get(Calendar.YEAR) == 2018 }
    }
}