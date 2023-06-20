package com.happymeerkat.avocado.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.happymeerkat.avocado.domain.repository.ListRepository
import javax.inject.Inject

class RebootBroadcastReceiver @Inject constructor(
    private val repository: ListRepository
): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
    }
}