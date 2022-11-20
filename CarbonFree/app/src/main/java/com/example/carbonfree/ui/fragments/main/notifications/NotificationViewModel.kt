package com.example.carbonfree.ui.fragments.main.notifications

import android.graphics.Bitmap
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//
//@HiltViewModel
//class NotificationViewModel @Inject constructor(
//    private val notificationBuilder: NotificationCompat.Builder,
//    private val notificationManager: NotificationManagerCompat
//) : ViewModel() {
//
//
//    fun showSimpleNotificationRecycler(contentTitle: String, contentText: String, bitmap: Bitmap){
//        notificationManager.notify(1, notificationBuilder
//            .setContentTitle(contentTitle)
//            .setContentText(contentText)
//            .setLargeIcon(bitmap)
//            .setAutoCancel(true)
//            .build())
//    }
//}