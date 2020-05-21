package com.armmask.annoyingex

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class AnnoyingExPrepWorker (private val context: Context, workParams: WorkerParameters): Worker(context, workParams){
    override fun doWork(): Result {
        (context.applicationContext as AnnoyingExApp).fetchAPI.fetchWithVolley(context)
        return Result.success()
    }
}