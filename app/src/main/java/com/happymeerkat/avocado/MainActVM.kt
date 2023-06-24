package com.happymeerkat.avocado

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActVM @Inject constructor(): ViewModel() {
    val visiblePermissionDialogueQueue = mutableListOf<String>()
    fun dismissDialog() {
        visiblePermissionDialogueQueue.removeLast()
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if(!isGranted) {
            visiblePermissionDialogueQueue.add(0, permission)
        }
    }
}