package com.butajlo.stacknow.presentation.base

import android.app.Application
import com.butajlo.stacknow.presentation.di.appModules
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)

        Timber.plant(Timber.DebugTree())
    }
}