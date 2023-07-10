package com.happymeerkat.avocado.presentation.screens.dialog

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.presentation.vm.EditItemVM
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewItemEditor(
    modifier: Modifier = Modifier,
    closeModal: () -> Unit,
    viewModel: EditItemVM = hiltViewModel(),
    currentCategory: Category,
    showDateDialog: () -> Unit
) {
    val state = viewModel.itemUIState.collectAsState().value
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current
    BackHandler(enabled = true) {
        closeModal()
    }

    Box(
        modifier = modifier
            .clickable { closeModal() }
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { }
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    value = state.title,
                    onValueChange = {viewModel.editTitle(it)},
                    placeholder = {Text("enter task")},
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if(state.title == "")
                                closeModal()
                            else
                                closeModalAndSave(
                                    closeModal = closeModal,
                                    save = {viewModel.createNewItem(currentCategory, context)},
                                    clearField = {
                                        viewModel.clearEditSlate()
                                    }
                                )
                        }
                    ),
                    singleLine = true,
                    colors = textFieldColors(
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = MaterialTheme.colorScheme.background,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
                        cursorColor = MaterialTheme.colorScheme.onPrimary
                    ),
                )
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 4.dp)
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = { showDateDialog() }) {
                        Icon(
                            tint = MaterialTheme.colorScheme.onBackground,
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = ""
                        )
                    }

                    state.dateDue?.let { Text(text = LocalDate.ofEpochDay(it).format(DateTimeFormatter.ofPattern("EEE, MMM dd")) + "  ") }
                    state.timeDue?.let { Text(text = Instant.ofEpochSecond(it).atZone(ZoneId.systemDefault().rules.getOffset(Instant.now())).toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"))) }
                }
            }

        }
    }

}

fun closeModalAndSave(
    closeModal: () -> Unit,
    save: () -> Unit,
    clearField: () -> Unit
) {
    closeModal()
    save()
    clearField()
}