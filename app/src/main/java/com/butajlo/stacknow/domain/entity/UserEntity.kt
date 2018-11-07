package com.butajlo.stacknow.domain.entity

data class UserEntity(
    val userId: Long?,
    val username: String,
    val profileImageUrl: String?,
    val type: Type
) {
    enum class Type {
        DOES_NOT_EXIST, BASIC, UNREGISTERED, MODERATOR, TEAM_ADMIN
    }
}