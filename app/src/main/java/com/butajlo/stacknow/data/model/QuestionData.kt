package com.butajlo.stacknow.data.model

import com.google.gson.annotations.SerializedName

data class QuestionData(
    val tags: List<String>,
    val owner: UserData,
    @SerializedName("question_id") val questionId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("link") val link: String,
    @SerializedName("is_answered") val isAnswered: Boolean,
    @SerializedName("view_count") val viewCount: Int,
    @SerializedName("favorite_count") val favoriteCount: Int,
    @SerializedName("down_vote_count") val downVoteCount: Int,
    @SerializedName("up_vote_count") val upVoteCount: Int,
    @SerializedName("answer_count") val answerCount: Int,
    @SerializedName("last_activity_date") val lastActivityDate: Long,
    @SerializedName("creation_date") val creationDate: Long,
    @SerializedName("last_edit_date") val lastEditDate: Long
)