package com.butajlo.stacknow.presentation.notifications

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.butajlo.stacknow.R
import com.butajlo.stacknow.domain.entity.NotificationEntity

fun NotificationEntity.toPushNotification(context: Context, channelId: String) =
        NotificationCompat.Builder(context, channelId)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_notification_small)
            .setOngoing(!isClosable)
            .setPriority(importance.toNotificationPriority())
            .build()

fun NotificationEntity.Importance.toNotificationPriority(): Int {
    return when(this) {
        NotificationEntity.Importance.DEFAULT -> NotificationCompat.PRIORITY_DEFAULT
        NotificationEntity.Importance.HIGH -> NotificationCompat.PRIORITY_HIGH
        NotificationEntity.Importance.LOW -> NotificationCompat.PRIORITY_LOW
        NotificationEntity.Importance.MAX -> NotificationCompat.PRIORITY_MAX
        NotificationEntity.Importance.MIN -> NotificationCompat.PRIORITY_MIN
    }
}

fun NotificationEntity.Importance.toNotificationManagerImportance(): Int {
    return when(this) {
        NotificationEntity.Importance.DEFAULT -> NotificationManagerCompat.IMPORTANCE_DEFAULT
        NotificationEntity.Importance.HIGH -> NotificationManagerCompat.IMPORTANCE_HIGH
        NotificationEntity.Importance.LOW -> NotificationManagerCompat.IMPORTANCE_LOW
        NotificationEntity.Importance.MAX -> NotificationManagerCompat.IMPORTANCE_MAX
        NotificationEntity.Importance.MIN -> NotificationManagerCompat.IMPORTANCE_MIN
    }
}