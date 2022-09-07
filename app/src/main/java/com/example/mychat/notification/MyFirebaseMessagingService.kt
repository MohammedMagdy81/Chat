package com.example.mychat.notification

import android.app.NotificationManager
import android.content.Context
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mychat.Data
import com.example.mychat.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.remoteMessage
import java.util.*

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)


    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        showNotification(message.notification?.title ?: "", message.notification?.body ?: "")
    }

    fun showNotification(title: String, text: String) {
        val notificationBuilder = NotificationCompat.Builder(this, Data.MESSAGES_CHANEL_ID)
        notificationBuilder.apply {
            setContentTitle(title)
            setContentText(text)
            setPriority(NotificationCompat.PRIORITY_DEFAULT)
            setAutoCancel(true)
            setSmallIcon(R.drawable.ic_baseline_message_24)

        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(Data.NOTIFICATIO_ID, notificationBuilder.build())
        }
    }
}