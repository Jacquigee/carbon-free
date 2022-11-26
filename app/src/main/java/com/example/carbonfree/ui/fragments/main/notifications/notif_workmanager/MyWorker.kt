package com.example.carbonfree.ui.fragments.main.notifications.notif_workmanager

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.carbonfree.ui.fragments.main.notifications.MyRequestNotification

class MyWorker (val context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {

        MyRequestNotification(context).createNotification(
            inputData.getString("contentTitle").toString(),
            inputData.getString("contentText").toString(),
//            inputData.getString("bitmap").toString().toInt()

        )

        Log.d("myWork", "my work was called")
        return Result.success()
    }

}