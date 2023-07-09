package com.happymeerkat.avocado.presentation.screens.dialog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.avocado.presentation.vm.EditItemVM
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.DatePickerColors
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateDialog(
    dateDialogState: MaterialDialogState,
    openTimeDialog: () -> Unit,
    viewModel: EditItemVM = hiltViewModel(),
) {
    val state = viewModel.itemUIState.collectAsState().value
    var pickedDate by remember{ mutableStateOf(LocalDate.now()) }
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(
                text = "Ok",
                onClick = {viewModel.setDateDue(pickedDate)},
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
            )
            negativeButton(
                text = "Cancel",
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
            )
        },
        backgroundColor = MaterialTheme.colorScheme.background
    ) {

        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
            waitForPositiveButton = false, // if you don't select this, the function in the text button will have the wrong value,
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = MaterialTheme.colorScheme.background,
                headerTextColor = MaterialTheme.colorScheme.onPrimary,
                calendarHeaderTextColor = MaterialTheme.colorScheme.onPrimary,
                dateActiveBackgroundColor = MaterialTheme.colorScheme.onPrimary,
                dateInactiveBackgroundColor = MaterialTheme.colorScheme.background,
                dateActiveTextColor = MaterialTheme.colorScheme.background,
                dateInactiveTextColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            pickedDate = it
        }
        TextButton(onClick = { viewModel.setDateDue(pickedDate); openTimeDialog(); }) {
            Text(
                text = state.timeDue?.let {
                    Instant.ofEpochSecond(it).atZone(
                        ZoneId.systemDefault().rules.getOffset(
                            Instant.now())).toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"))
                } ?: "Set time as well", fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}