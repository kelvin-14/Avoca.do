package com.happymeerkat.avocado.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.domain.repository.ListRepository
import com.happymeerkat.avocado.domain.use_case.ListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditItemVM @Inject constructor(
    private val listUseCases: ListUseCases
): ViewModel() {
    private val _itemUIState = MutableStateFlow(ItemUIState())
    val itemUIState: StateFlow<ItemUIState> = _itemUIState

    fun editTitle(newTitle: String) {
        _itemUIState.value = itemUIState.value.copy(title = newTitle)
    }

    fun upsertListItem() {
        viewModelScope.launch {
            listUseCases.upsertItem(ListItem(
                title = _itemUIState.value.title,
                description = _itemUIState.value.description,
                category = _itemUIState.value.category, // TODO:change to
                dateMade = _itemUIState.value.dateMade,
                dateDue = _itemUIState.value.dueDate
            ))
        }
    }
}

data class ItemUIState(
    val title: String = "",
    val description: String = "",
    val category: String = "ALL",
    val dateMade: Long? = null,
    val dueDate: Long? = null
)