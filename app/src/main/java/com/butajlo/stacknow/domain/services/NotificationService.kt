package com.butajlo.stacknow.domain.services

import com.butajlo.stacknow.domain.entity.NotificationEntity

interface NotificationService {

    /** Shows provided [notification] **/
    fun show(notification: NotificationEntity)

}