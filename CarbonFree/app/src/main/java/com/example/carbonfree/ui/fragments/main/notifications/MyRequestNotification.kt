package com.example.carbonfree.ui.fragments.main.notifications

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.NavDeepLinkBuilder
import com.example.carbonfree.R
import com.example.carbonfree.ui.activities.MainActivity

class MyRequestNotification(
    val context: Context,
//    val resources: Resources
) {

//    private val resources: Resources = Resources.getSystem()

    companion object {
        const val CHANNEL_ID = "scheduled_notifications"
        const val CHANNEL_NAME = "Scheduled Reminder"
        const val NOTIFICATION_ID = 1
        const val CHANNEL_DESCRIPTION =
            "This Notification Reminds You To Get Carbon Free At A Set Time Interval"
    }

    fun createNotification(
        title: String, message: String,
//        bitmap: Int
    ) {
        createNotificationChannel()

//        val myBitmap = BitmapFactory.decodeResource(resources, bitmap)

//                val bitmap = BitmapFactory.decodeResource(resources, notificationItem.carbonFreeNotificationLargeIcon)

//        val myBitmap = BitmapFactory.decodeResource(context.applicationContext.resources, bitmap)

        val intentPending = NavDeepLinkBuilder(context)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.secondFragment)

            .createPendingIntent()

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setLargeIcon(myBitmap)

            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(intentPending)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)

    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESCRIPTION
            }

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}