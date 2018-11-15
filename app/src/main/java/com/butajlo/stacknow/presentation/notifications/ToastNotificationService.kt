package com.butajlo.stacknow.presentation.notifications

import android.content.Context
import android.widget.Toast
import com.butajlo.stacknow.domain.entity.NotificationEntity
import com.butajlo.stacknow.domain.services.NotificationService

class ToastNotificationService(private val context: Context) : NotificationService {

    override fun show(notification: NotificationEntity) {
        Toast.makeText(context, notification.content, Toast.LENGTH_LONG).show()
    }

    override fun close(notificationId: Int) = Unit
}