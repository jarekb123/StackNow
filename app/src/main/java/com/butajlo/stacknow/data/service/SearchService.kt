package com.butajlo.stacknow.data.service

import com.butajlo.stacknow.data.model.SearchResponseData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search")
    fun findQuestion(
        @Query("intitle") inTitle: String,
        @Query("tagged") tags: String,
        @Query("page") page: Int,
        @Query("order") order: String,
        @Query("site") site: String
    ) : Single<SearchResponseData>
}