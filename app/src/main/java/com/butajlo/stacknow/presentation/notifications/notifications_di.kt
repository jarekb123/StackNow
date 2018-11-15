package com.butajlo.stacknow.presentation.notifications

import com.butajlo.stacknow.domain.services.NotificationService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val notificationModule = module {
    single(name = "push") { PushNotificationService(androidContext()) as NotificationService }
    single(name = "toast") { ToastNotificationService(androidContext()) as NotificationService }
}