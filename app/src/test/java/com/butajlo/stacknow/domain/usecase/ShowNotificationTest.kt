package com.butajlo.stacknow.domain.usecase

import com.butajlo.stacknow.domain.entity.NotificationEntity
import com.butajlo.stacknow.domain.services.NotificationService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class ShowNotificationTest {

    private val notificationService = mock<NotificationService>()
    private val notification = mock<NotificationEntity>()

    @Test
    fun showNotification_ShouldCallShowOnNotificationService() {
        showNotification(service = notificationService, notification = notification)
        verify(notificationService).show(any())
    }
}