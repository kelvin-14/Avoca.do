package com.happymeerkat.avocado.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.happymeerkat.avocado.domain.repository.ListRepository
import com.happymeerkat.avocado.domain.use_case.ListUseCases
import com.happymeerkat.avocado.updateAppWidgetThroughContext
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class WidgetCheckboxBroadcastReceiver: BroadcastReceiver() {
    @Inject
    lateinit var listUseCases: ListUseCases
    var getListItemsJob: Job? = null
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context?, intent: Intent?) {
        val extras = intent?.extras
        if(extras != null) {
            val item_id: Int = extras.getInt(ITEM_ID) // returns 0 if key not there
            if(item_id != 0) {
                CoroutineScope(Dispatchers.IO).launch {
                    val list_item = listUseCases.getItemById(item_id)
                    if (list_item != null) {
                        listUseCases.upsertItem(
                            list_item.copy(
                                completed = !list_item.completed
                            )
                        )
                        getListItemsJob?.cancel()
                        getListItemsJob =  listUseCases.getItems().onEach {
                            context?.updateAppWidgetThroughContext(it)
                        }.launchIn(CoroutineScope(Dispatchers.IO))
                    }

                }
            }
        }
    }

    companion object {
        val ITEM_ID: String = "ITEM_ID"
        val ACTION_CHECK_BOX_CHANGE: String = "com.happymeerkat.avocado.checkboxChange"
    }
}