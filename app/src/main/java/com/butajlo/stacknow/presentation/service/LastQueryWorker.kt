package com.butajlo.stacknow.presentation.service

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.butajlo.stacknow.R
import com.butajlo.stacknow.domain.entity.NotificationEntity
import com.butajlo.stacknow.domain.repository.SearchStackRepository
import com.butajlo.stacknow.domain.services.NotificationService
import com.butajlo.stacknow.domain.usecase.findQuestions
import com.butajlo.stacknow.domain.usecase.showNotification
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class LastQueryWorker(private val context: Context, private val params: WorkerParameters) :
    Worker(context, params), KoinComponent {

    private val searchRepository by inject<SearchStackRepository>()
    private val notificationService by inject<NotificationService>(name = "push")
    private val subscription = CompositeDisposable()

    override fun doWork(): Result {
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
            .doOnSubscribe { showPushNotification(query) }
            .subscribe()
            .addTo(subscription)
    }

    private fun showPushNotification(query: String) {
        context.apply {
            val notification = NotificationEntity(
                id = NOTIFICATION_ID,
                title = getString(R.string.query_notification_title),
                content = getString(R.string.query_notification_text, query),
                channelName = CHANNEL_ID,
                importance = NotificationEntity.Importance.DEFAULT,
                isClosable = false
            )
            showNotification(service = notificationService, notification = notification)
        }
    }

    companion object {
        const val WORK_LAST_QUERY = "WORK_LAST_QUERY"
        const val EXTRA_QUERY = "EXTRA_QUERY"
        const val CHANNEL_ID = "QUERY_CHANNEL"
        const val NOTIFICATION_ID = 1
    }
}