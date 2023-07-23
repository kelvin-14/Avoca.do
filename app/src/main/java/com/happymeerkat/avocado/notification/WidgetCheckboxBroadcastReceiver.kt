package com.happymeerkat.avocado.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.happymeerkat.avocado.domain.repository.ListRepository
import com.happymeerkat.avocado.domain.use_case.ListUseCases
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WidgetCheckboxBroadcastReceiver: BroadcastReceiver() {
    @Inject
    lateinit var listUseCases: ListUseCases
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("WIDGET", intent.toString())
        val action = intent?.action
        val extras = intent?.extras
        if(!action.equals(null)) {
            Log.d("WIDGET ", action.toString())
        }else {
            Log.d("WIDGET ", "No intent action")
        }

        if (extras != null) {
            Log.d("WIDGET extras value ", extras.getInt("ITEM_ID").toString())
        }


    }

    companion object {
        val EXTRA_SUBMISSION_ID: String = "ITEM_ID"
        val ACTION_CHECK_BOX_CHANGE: String = "com.happymeerkat.avocado.checkboxChange"
    }
}