package com.happymeerkat.avocado.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.domain.repository.ListRepository
import com.happymeerkat.avocado.notification.AlarmReceiver.SerializableHelper.serializable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnCompletedBroadCastReceiver @Inject constructor(
    private val listRepository: ListRepository
): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val listItem = intent?.serializable("list_item") as? ListItem
        if(listItem != null) {
            listItem.completed = true
        }

        CoroutineScope(IO).launch {
            listItem?.let {
                listRepository.upsertItem(listItem)
            }
        }

        if(context != null && listItem != null) {
            NotificationManagerCompat.from(context).cancel(null, listItem.id!!)
        }
    }
}