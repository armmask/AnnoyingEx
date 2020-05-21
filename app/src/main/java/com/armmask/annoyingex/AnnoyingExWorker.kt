package com.armmask.annoyingex

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class AnnoyingExWorker(private val context: Context, workParams:WorkerParameters): Worker(context, workParams) {

    override fun doWork(): Result {
        //NOTIFICATION TIEM!!!
    }
}