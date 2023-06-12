package com.happymeerkat.avocado.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.domain.use_case.ListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val listUseCases: ListUseCases
): ViewModel() {
    private val _mainUIState: MutableStateFlow<MainUIState> = MutableStateFlow(MainUIState())
    val mainUIState: StateFlow<MainUIState> = _mainUIState
    var getListItemsJob: Job? = null
    init {
        getAllListItems()
    }
    fun getAllListItems() {
        getListItemsJob?.cancel()
        getListItemsJob = listUseCases
            .getItems(category = _mainUIState.value.category.name)
            .onEach { listOfItems ->
                _mainUIState.value = mainUIState.value.copy(listItems = listOfItems)
            }
            .launchIn(viewModelScope)
    }

    fun enterEditState() {
        _mainUIState.value = mainUIState.value.copy(editState = true)
    }

    fun exitEditState() {
        _mainUIState.value = mainUIState.value.copy(editState = true)
    }

    fun completeItem(item: ListItem) {
        item.completed = true
    }

    fun undoCompleteItem(item: ListItem) {
        item.completed = false
    }
}

data class MainUIState(
    val listItems: List<ListItem> = emptyList() ,
    val category: Category = Category("ALL"),
    val categories: List<Category> = emptyList(),
    val selected: Set<Int> = emptySet(), // e.g when deleting, put the id of selected items here
    val editState: Boolean = false,
    )