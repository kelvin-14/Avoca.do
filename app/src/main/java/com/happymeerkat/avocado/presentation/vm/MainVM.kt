package com.happymeerkat.avocado.presentation.vm

import android.util.Log
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
    var getCategoriesJob: Job? = null
    init {
        getAllListItems()
        getAllCategories()
    }
    fun getAllListItems() {
        getListItemsJob?.cancel()
        getListItemsJob = listUseCases
            .getItems()
            .onEach { listOfItems ->
                _mainUIState.value = mainUIState.value.copy(
                    listItems = listOfItems
                )
            }
            .launchIn(viewModelScope)
    }

    fun getAllCategories() {
        getCategoriesJob?.cancel()
        getCategoriesJob = listUseCases
            .getAllCategories()
            .onEach { listOfCategories ->
                _mainUIState.value = mainUIState.value.copy(
                    categories = listOfCategories
                )
            }
            .launchIn(viewModelScope)
    }

    fun changeCurrentCategory(category: Category) {
        _mainUIState.value = mainUIState.value.copy(currentCategory = category)
        Log.d("CATEGORY", mainUIState.value.currentCategory.name)
    }

    sealed interface Response
    object Success: Response
    data class Failure (val errorMessage: String): Response
    fun createNewCategory(category: Category): Response {
        if (_mainUIState.value.categories.contains(category)) {
            return Failure("category already exists")
        }else{
            viewModelScope.launch {
                listUseCases.createCategory(category)
            }
            return Success
        }
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

    fun editCurrentCategoryName() {
        // change category name in db but make sure no such name exists first

        // change category name for all tasks with that name

    }

    fun deleteCurrentCategory() {
        // delete all tasks with that name

        // delete category from db
    }

    fun deleteCompletedTasks() {
        // delete all tasks of current category where completed = true
    }

}

data class MainUIState(
    val listItems: List<ListItem> = emptyList() ,
    val listCompletedItems: List<ListItem> = emptyList(),
    val currentCategory: Category = Category("All"),
    val categories: List<Category> = emptyList(),
    val selected: Set<Int> = emptySet(), // e.g when deleting, put the id of selected items here
    val editState: Boolean = false,
    )