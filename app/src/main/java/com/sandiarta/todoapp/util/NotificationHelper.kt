package com.sandiarta.todoapp.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.R
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.sandiarta.todoapp.view.MainActivity

class NotificationHelper(val context: Context) {
    private val CHANNEL_ID = "todo_channel_id"
    private val NOTIFICATION_ID = 1

    companion object {
        val REQUEST_NOTIF = 100
    }

    private  fun CreateNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_ID,NotificationManager.IMPORTANCE_DEFAULT).apply {
                description="Todo Channel description"
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    fun CreateNotification( title:String, message:String) {
        CreateNotificationChannel()
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context,0, intent,
            PendingIntent.FLAG_IMMUTABLE)
        val icon = BitmapFactory.decodeResource(context.resources, R.drawable.)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.checklist)
            .setLargeIcon(icon)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(icon)
                    .bigLargeIcon(null)
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        try {
            NotificationManagerCompat.from(context)
                .notify(NOTIFICATION_ID, notification)
        } catch (e:SecurityException) {
            Log.e("error", e.toString())
        }






    }



}
