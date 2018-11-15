package com.butajlo.stacknow.domain.usecase

import com.butajlo.stacknow.domain.entity.NotificationEntity
import com.butajlo.stacknow.domain.services.NotificationService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class ShowNotificationTest {

    private val notificationService = mock<NotificationService>()
    private val notification = NotificationEntity(
        id = 1,
        title = "title",
        content = "content",
        importance = NotificationEntity.Importance.DEFAULT,
        channelName = "channel",
        isClosable = false
    )


    @Test
    fun showNotification_ShouldCallShowOnNotificationService() {
        showNotification(service = notificationService, notification = notification)
        verify(notificationService).show(notification)
    }
}