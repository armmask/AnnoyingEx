package com.armmask.annoyingex

import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit

class AnnoyingExManager(private val context: Context) {

    private var workManager = WorkManager.getInstance(context)

    init {
        startAnnoyingPrepFetch()
    }

    private fun startAnnoyingPrepFetch() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest = PeriodicWorkRequestBuilder<AnnoyingExPrepWorker>(2, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        workManager.enqueue(workRequest)
    }

    fun startAnnoyingEx() {

        Log.i("armmask", "button works")
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<AnnoyingExWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        Log.i("armmask", "queuing request")
        workManager.enqueue(workRequest)
    }

    fun stopAnnoyingEx() {
        workManager.cancelAllWork()
    }

}