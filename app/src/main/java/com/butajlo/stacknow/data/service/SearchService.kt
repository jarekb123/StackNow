package com.butajlo.stacknow.data.service

import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search")
    fun findQuestion(
        @Query("intitle") inTitle: String,
        @Query("tagged") tags: String,
        @Query("page") page: Int,
        @Query("order") order: String = "desc",
        @Query("site") site: String = "stackoverflow"
    )
}