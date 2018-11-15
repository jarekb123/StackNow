package com.butajlo.stacknow.domain.entity

import java.util.concurrent.TimeUnit

data class NotificationEntity(
    val id: Int,
    val title: String,
    val content: String,
    val channelName: String,
    val isClosable: Boolean,
    val importance: Importance
) {
    enum class Importance {
        DEFAULT, LOW, MIN, HIGH, MAX
    }
}