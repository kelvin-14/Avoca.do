package com.happymeerkat.avocado

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.use_case.ListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActVM @Inject constructor(
    private val listUseCases: ListUseCases
): ViewModel() {
    val visiblePermissionDialogueQueue = mutableListOf<String>()

    init {
        invokeDBCreation()
    }

    private fun invokeDBCreation() {
        viewModelScope.launch {
            listUseCases.categoryUpsert(Category(1, "All"))
        }

    }
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