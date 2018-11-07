package com.butajlo.stacknow.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponseData(
    val items: List<QuestionData>,
    @SerializedName("has_more") val hasMore: Boolean
)