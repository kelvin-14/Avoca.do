package com.happymeerkat.avocado.notification

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.app.PendingIntent.getActivity
import android.app.PendingIntent.getBroadcast
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.happymeerkat.avocado.MainActivity
import com.happymeerkat.avocado.R
import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.notification.AlarmReceiver.SerializableHelper.serializable
import java.io.Serializable

class AlarmReceiver: BroadcastReceiver() {

    private var notificationManager: NotificationManagerCompat? = null

    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        val listItem = intent?.serializable("list_item") as? ListItem

        val markCompletedIntent = Intent(context, MainActivity::class.java)
        markCompletedIntent.flags =Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent: PendingIntent = getActivity(context, 0, markCompletedIntent, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)

        val intent1 = Intent(context, OnCompletedBroadCastReceiver::class.java).apply {
            putExtra("list_item", listItem)
        }
        val pendingIntent1: PendingIntent? = listItem?.let { getBroadcast(context, it.id!!, intent1, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE) }
        val action1 = NotificationCompat.Action.Builder(0, "Completed", pendingIntent1).build()

        val notification = context?.let {
            NotificationCompat.Builder(it, "to_do_reminders")
                .setContentTitle("Task Reminder")
                .setContentText(listItem?.description)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .addAction(action1)
                .build()
        }

        notificationManager = context?.let { NotificationManagerCompat.from(it) }
        notification?.let { listItem?.let { it1 -> notificationManager?.notify(it1.id!!, it); Log.d("ALARM NOTIF", "notification sent") } }
    }



    object SerializableHelper {
        inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
            else -> @Suppress("DEPRECATION") getSerializable(key) as? T
        }

        inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
            else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
        }
    }

}