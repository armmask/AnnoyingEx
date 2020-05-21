package com.armmask.annoyingex

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.random.Random

class AnnoyingExWorker(private val context: Context, workParams:WorkerParameters): Worker(context, workParams) {

    companion object {
        const val CHANNEL_ID = "ARMMASKCHANNEL"
    }

    private val annoyingList: List<String> = (context.applicationContext as AnnoyingExApp).fetchAPI.annoyingList
    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createNotificationChannel()
    }

    override fun doWork(): Result {
        //NOTIFICATION TIEM!!!

        Log.i("armmask", "We sent message")

        val annoyingText = annoyingList[Random.nextInt(annoyingList.size)]

        val intent = Intent(context, MainActivity::class.java).apply() {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("message", annoyingText)
        }
        val pendingActivity = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        var builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_donut_large_black_24dp)
            .setContentTitle(AnnoyingExApp.NAME)
            .setContentText(annoyingText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingActivity)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(Random.nextInt(), builder)
        return Result.success()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Annoying EX"
            val descriptionText = "The Message from the annoying EX"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }
}