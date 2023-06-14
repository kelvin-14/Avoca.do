package com.happymeerkat.avocado.presentation.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happymeerkat.avocado.domain.model.Category
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

    private suspend fun upsertListItem(item: ListItem) {
        listUseCases.upsertItem(item)
    }

    fun createNewItem(category: Category) {
        viewModelScope.launch {
            val item = ListItem(
                title = _itemUIState.value.title,
                description = _itemUIState.value.description,
                categoryId = category.id, // TODO:change to
                dateMade = _itemUIState.value.dateMade,
                dateDue = _itemUIState.value.dueDate
            )
            upsertListItem(item)
        }
    }

    fun updateItem(item: ListItem) {
        viewModelScope.launch {
            Log.d("TO BE UPDATED", item.id.toString()+ " " + item.title + " " + item.completed.toString())
            upsertListItem(item)
        }
    }


//    fun collapseCategoryMenu() {
//        _itemUIState.value = itemUIState.value.copy(categoryExpanded = false)
//        Log.d("MENU", itemUIState.value.categoryExpanded.toString())
//    }
//
//    fun expandCategoryMenu() {
//        _itemUIState.value = itemUIState.value.copy(categoryExpanded = true)
//        Log.d("MENU", itemUIState.value.categoryExpanded.toString())
//    }
}

data class ItemUIState(
    val title: String = "",
    val description: String = "",
    val category: String = "ALL",
    val dateMade: Long? = null,
    val dueDate: Long? = null,
    var categoryExpanded: Boolean = false
)