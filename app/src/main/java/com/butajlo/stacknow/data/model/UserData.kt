package com.butajlo.stacknow.data.model

import com.google.gson.annotations.SerializedName

data class UserData(
    val reputation: Int?,
    @SerializedName("user_id") val userId: Int?,
    @SerializedName("user_type") val userType: Type,
    @SerializedName("profile_image") val profileImageUrl: String?,
    @SerializedName("display_name") val displayName: String,
    val link: String?
) {
    enum class Type {
        @SerializedName("unregistered") UNREGISTERED,
        @SerializedName("registered") REGISTERED,
        @SerializedName("moderator") MODERATOR,
        @SerializedName("team_admin") TEAM_ADMIN,
        @SerializedName("does_not_exist") DOES_NOT_EXIST
    }
}