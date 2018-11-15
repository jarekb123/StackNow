package com.butajlo.stacknow.presentation.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import com.butajlo.stacknow.domain.entity.NotificationEntity
import com.butajlo.stacknow.domain.services.NotificationService

class PushNotificationService(private val context: Context) : NotificationService {

    override fun show(notification: NotificationEntity) {
        NotificationManagerCompat.from(context)
            .notify(
                notification.id,
                notification.toPushNotification(context, createChannel(notification))
            )
    }

    override fun close(notificationId: Int) {
        NotificationManagerCompat.from(context).cancel(notificationId)
    }

    /**
     * Creates NavigationChannel
     * @return Channel's ID
     */
    private fun createChannel(notification: NotificationEntity): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = notification.channelName
            val importance = notification.importance.toNotificationManagerImportance()
            val channel = NotificationChannel(name, name, importance)

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        return notification.channelName
    }
}