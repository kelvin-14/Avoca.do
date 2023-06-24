package com.happymeerkat.avocado.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.AndroidUiDispatcher.Companion.Main
import com.happymeerkat.avocado.MainActivity
import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.domain.repository.ListRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject
@AndroidEntryPoint
class RebootBroadcastReceiver @Inject constructor(
): BroadcastReceiver() {
    @Inject lateinit var repository: ListRepository
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        val time =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                LocalTime.now().toEpochSecond(
                    LocalDate.now(),
                    ZoneId.systemDefault().rules.getOffset(Instant.now())
                )
            } else {
                //TODO:
                0
            }

        CoroutineScope(Main).launch {
            val todos = repository.getActiveAlarms(time)
            Log.d("PENDING ALARMS", todos.size.toString())
            todos.forEach { item -> setAlarm(item, context) }
        }
    }

    private fun setAlarm(listItem: ListItem, context: Context?) {
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra("list_item", listItem)
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND )
        val pendingIntent = PendingIntent.getBroadcast(context, listItem.id!!, intent, PendingIntent.FLAG_IMMUTABLE)
        val mainActivityIntent = Intent(context, MainActivity::class.java)
        val basicPendingIntent = PendingIntent.getActivity(context, listItem.id, mainActivityIntent, PendingIntent.FLAG_IMMUTABLE)
        Log.d("MILLISECONDS info ", (listItem.timeDue?.times(1000)).toString())
        val clockInfo = AlarmManager.AlarmClockInfo((listItem.timeDue!!.times(1000)), basicPendingIntent)
        alarmManager.setAlarmClock(clockInfo, pendingIntent)
    }
}