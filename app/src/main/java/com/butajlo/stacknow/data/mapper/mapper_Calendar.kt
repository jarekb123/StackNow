package com.butajlo.stacknow.data.mapper

import java.util.*

fun Long.toCalendar(): Calendar {
    return Calendar.getInstance().also {
        it.time = Date(this)
    }
}