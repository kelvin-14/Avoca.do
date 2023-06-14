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
            .categoryGetAll()
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

    fun deleteCurrentCategory() {
        viewModelScope.launch {
            listUseCases.categoryDelete(_mainUIState.value.currentCategory)
        }
    }

    sealed interface Response
    object Success: Response
    data class Failure (val errorMessage: String): Response
    fun createNewCategory(category: Category): Response {
        if (_mainUIState.value.categories.filter { it.name == category.name }.isNotEmpty()) {
            return Failure("category already exists")
        }else{
            viewModelScope.launch {
                listUseCases.categoryUpsert(category)
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

    fun editCurrentCategoryName(newName: String) {
        val currentCategoryId = _mainUIState.value.currentCategory.id
        if(!categoryNameExists(newName)) {
            viewModelScope.launch {
                listUseCases.categoryUpsert(Category(id = currentCategoryId, name = newName))
            }
        }else {
            Log.d("NAME EXISTS", "NAME EXISTS")
        }
    }

    fun deleteCompletedTasks() {
        viewModelScope.launch {
            listUseCases.deleteCompletedTasks(_mainUIState.value.currentCategory)
        }
    }

    private fun categoryNameExists(name: String): Boolean {
        return _mainUIState.value.categories.filter { it.name == name }.isNotEmpty()
    }

}

data class MainUIState(
    val listItems: List<ListItem> = emptyList() ,
    val listCompletedItems: List<ListItem> = emptyList(),
    val currentCategory: Category = Category(name = "All"),
    val categories: List<Category> = emptyList(),
    val selected: Set<Int> = emptySet(), // e.g when deleting, put the id of selected items here
    val editState: Boolean = false,
    )