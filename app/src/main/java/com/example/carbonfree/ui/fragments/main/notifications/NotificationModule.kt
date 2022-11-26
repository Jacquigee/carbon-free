package com.example.carbonfree.ui.fragments.main.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.carbonfree.R
import com.example.carbonfree.ui.activities.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//@Module
//@InstallIn(SingletonComponent::class)
//object NotificationModule {
//
//
//    @Singleton
//    @Provides
//    fun provideNotificationBuilder(@ApplicationContext context: Context): NotificationCompat.Builder {
//
//        val intentPending = NavDeepLinkBuilder(context)
//            .setComponentName(MainActivity::class.java)
//            .setGraph(R.navigation.nav_graph)
//            .setDestination(R.id.secondFragment)
//
//            .createPendingIntent()
//
//        return NotificationCompat.Builder(
//            context, "NOTIFICATION CHANNEL ID"
//        )
//            .setContentTitle("")
//            .setContentText("")
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setVisibility(VISIBILITY_PUBLIC)
//            .setContentIntent(intentPending)
//
//    }
//
//    @Singleton
//    @Provides
//    fun provideNotificationManager(@ApplicationContext context: Context): NotificationManagerCompat {
//        val notificationManager = NotificationManagerCompat.from(context)
//
//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//             val channel =   NotificationChannel(
//                "NOTIFICATION CHANNEL ID",
//                "Scheduled Reminder Notifications",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//
//             notificationManager.createNotificationChannel(channel)
//
//        }
//        return notificationManager
//    }
//}