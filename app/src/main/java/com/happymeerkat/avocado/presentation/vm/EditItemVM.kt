package com.happymeerkat.avocado.presentation.vm

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.domain.use_case.ListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.temporal.ChronoField
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNewItem(category: Category) {
        viewModelScope.launch {
            val item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ListItem(
                    title = _itemUIState.value.title,
                    description = _itemUIState.value.description,
                    categoryId = category.id, // TODO:change to
                    dateMade = _itemUIState.value.dateMade?.toEpochDay(),
                    dateDue = _itemUIState.value.dateDue?.toEpochDay(),
                    timeDue = _itemUIState.value.timeDue?.toEpochSecond(LocalDate.now(),ZoneOffset.UTC)
                )
            } else {
                TODO("VERSION.SDK_INT < S")
            }
            upsertListItem(item)
        }
    }

    fun updateItem(item: ListItem) {
        viewModelScope.launch {
            Log.d("TO BE UPDATED", item.id.toString()+ " " + item.title + " " + item.completed.toString())
            upsertListItem(item)
        }
    }

    fun setTime(time: LocalTime) {
        _itemUIState.value = itemUIState.value.copy(timeDue = time)
    }

    fun setDate(date: LocalDate) {
        _itemUIState.value = itemUIState.value.copy(dateDue = date)
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun getCurrentTime(): Long {
//        return LocalDate.now().getLong(ChronoField.INSTANT_SECONDS)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun changeDateDue(date: LocalDate) {
//        _itemUIState.value = itemUIState.value.copy(dateDue = date.getLong(ChronoField.INSTANT_SECONDS))
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun changeTimeDue(time: LocalTime) {
//        _itemUIState.value = itemUIState.value.copy(timeDue = time.getLong(ChronoField.INSTANT_SECONDS))
//    }
//
//    fun showDateDialog() {}
}

data class ItemUIState(
    val title: String = "",
    val description: String = "",
    val categoryId: String = "",
    val dateMade: LocalDate? = null,
    val dateDue: LocalDate? = null,
    val timeDue: LocalTime? = null,
    val completed: Boolean = false,
    var categoryExpanded: Boolean = false,
)

//var pickedDate by remember{ mutableStateOf(LocalDate.now()) }
//var pickedTime by remember{ mutableStateOf(LocalTime.NOON) }
//val formattedDate by remember {
//    derivedStateOf {
//        DateTimeFormatter
//            .ofPattern("MMM dd yyyy")
//            .format(pickedDate)
//    }
//}
//
//val formattedTime by remember {
//    derivedStateOf {
//        DateTimeFormatter
//            .ofPattern("hh:mm")
//            .format(pickedTime)
//    }
//}
//
//val dateDialogState = rememberMaterialDialogState()
//val timeDialogState = rememberMaterialDialogState()