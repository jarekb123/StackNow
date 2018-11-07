package com.butajlo.stacknow.data.mapper

import com.butajlo.stacknow.data.model.UserData
import com.butajlo.stacknow.domain.entity.UserEntity

fun UserData.toEntity(): UserEntity =
    UserEntity(
        userId = userId?.toLong(),
        username = displayName,
        profileImageUrl = profileImageUrl,
        type = userType.toEntity()
    )

private fun UserData.Type.toEntity(): UserEntity.Type {
    return when (this) {
        UserData.Type.UNREGISTERED -> UserEntity.Type.UNREGISTERED
        UserData.Type.REGISTERED -> UserEntity.Type.BASIC
        UserData.Type.MODERATOR -> UserEntity.Type.MODERATOR
        UserData.Type.TEAM_ADMIN -> UserEntity.Type.TEAM_ADMIN
        UserData.Type.DOES_NOT_EXIST -> UserEntity.Type.DOES_NOT_EXIST
    }
}