package com.gustavofaria.moonphases.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.M
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_DEFAULT
import com.gustavofaria.moonphases.R
import com.gustavofaria.moonphases.ui.activity.MainActivity

class NotificationReceiver : BroadcastReceiver() {
    companion object {
        private const val NOTIFICATION_COMPAT_CHANNEL_ID = "default"
        private const val NOTIFICATION_CHANNEL_ID = "10001"
        private const val NOTIFICATION_CHANNEL_NAME = "notification_channel_name"
        private const val NOTIFICATION_ID = "notification_id"
        private const val NOTIFICATION_ID_VALUE = 1
        private const val NOTIFICATION = "notification"
        private const val NOTIFICATION_REQUEST_CODE = 0
        private val NOTIFICATION_FLAGS =
            if (SDK_INT >= M) FLAG_IMMUTABLE or FLAG_UPDATE_CURRENT else FLAG_UPDATE_CURRENT

        fun getNotification(
            context: Context,
            title: String,
            content: String
        ): Notification {
            val builder: NotificationCompat.Builder =
                NotificationCompat.Builder(context, NOTIFICATION_COMPAT_CHANNEL_ID)
            val mainActivityIntent = Intent(context, MainActivity::class.java)
            builder.setContentTitle(title)
            builder.setContentText(content)
            builder.setSmallIcon(R.drawable.ic_action_bar)
            builder.setChannelId(NOTIFICATION_CHANNEL_ID)
            builder.setAutoCancel(true)
            builder.setContentIntent(
                PendingIntent.getActivity(
                    context,
                    NOTIFICATION_REQUEST_CODE,
                    mainActivityIntent,
                    NOTIFICATION_FLAGS
                )
            )
            builder.priority = PRIORITY_DEFAULT
            return builder.build()
        }

        fun getPendingIntent(context: Context, notification: Notification): PendingIntent? {
            val notificationIntent = Intent(context, NotificationReceiver::class.java)
            notificationIntent.putExtra(NOTIFICATION_ID, NOTIFICATION_ID_VALUE)
            notificationIntent.putExtra(NOTIFICATION, notification)
            return PendingIntent.getBroadcast(
                context,
                NOTIFICATION_REQUEST_CODE,
                notificationIntent,
                NOTIFICATION_FLAGS
            )
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val notification = intent.extras?.getParcelable(NOTIFICATION) as Notification?

        if (SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                importance
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val id = intent.getIntExtra(NOTIFICATION_ID, NOTIFICATION_ID_VALUE)
        notificationManager.notify(id, notification)
    }
}
