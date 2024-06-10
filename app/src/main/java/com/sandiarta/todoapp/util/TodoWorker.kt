package com.sandiarta.todoapp.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class TodoWorker(context: Context, params:WorkerParameters): Worker(context,params) {

    override fun doWork(): Result {
        NotificationHelper(applicationContext).CreateNotification(
            inputData.getString("title").toString(),
            inputData.getString("message").toString())
        return Result.success()
    }
}
