package com.butajlo.stacknow.domain.usecase

import com.butajlo.stacknow.domain.entity.NotificationEntity
import com.butajlo.stacknow.domain.services.NotificationService

fun showNotification(service: NotificationService, notification: NotificationEntity) {
    service.show(notification)
}

