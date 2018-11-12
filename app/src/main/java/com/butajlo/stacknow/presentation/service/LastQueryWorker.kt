package com.butajlo.stacknow.presentation.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.butajlo.stacknow.R
import com.butajlo.stacknow.domain.repository.SearchStackRepository
import com.butajlo.stacknow.domain.usecase.findQuestions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class LastQueryWorker(private val context: Context, private val params: WorkerParameters) :
    Worker(context, params), KoinComponent {

    private val searchRepository by inject<SearchStackRepository>()
    private val subscription = CompositeDisposable()

    override fun doWork(): Result {
        createNotificationChannel()
        val query = params.inputData.getString(EXTRA_QUERY)
        query?.run { updateQuery(this) }

        return Result.SUCCESS
    }

    override fun onStopped() {
        subscription.clear()
        super.onStopped()
    }

    private fun updateQuery(query: String) {
        subscription.clear()

        findQuestions(repository = searchRepository, searchString = query, page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showNotification(query) }
            .subscribe()
            .addTo(subscription)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.query_notification_channel_name)
            val description = context.getString(R.string.query_notification_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(query: String) {
        context.apply {
            val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            notificationBuilder
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentTitle(getString(R.string.query_notification_title))
                .setContentText(getString(R.string.query_notification_text, query))
                .setSmallIcon(R.drawable.ic_notification_small)
                .setOngoing(true)
                .priority = NotificationCompat.PRIORITY_DEFAULT

            NotificationManagerCompat.from(this).apply {
                notify(NOTIFICATION_ID, notificationBuilder.build())
            }
        }
    }

    companion object {
        const val WORK_LAST_QUERY = "WORK_LAST_QUERY"
        const val EXTRA_QUERY = "EXTRA_QUERY"
        const val CHANNEL_ID = "QUERY_CHANNEL"
        const val NOTIFICATION_ID = 1
    }
}