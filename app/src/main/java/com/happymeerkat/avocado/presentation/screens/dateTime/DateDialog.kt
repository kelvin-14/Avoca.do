package com.happymeerkat.avocado.presentation.screens.dateTime

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.avocado.presentation.vm.EditItemVM
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateDialog(
    dateDialogState: MaterialDialogState,
    openTimeDialog: () -> Unit,
    viewModel: EditItemVM = hiltViewModel(),
) {
    var pickedDate by remember{ mutableStateOf(LocalDate.now()) }
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "Ok", onClick = {viewModel.setDate(pickedDate)})
            negativeButton(text = "Cancel")
        }
    ) {

        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
            allowedDateValidator = { it.dayOfMonth % 2 == 1 }
        ) {
            pickedDate = it
        }
        TextButton(onClick = { openTimeDialog() }) {
            Text("Set time as well", fontSize = 20.sp)
        }
    }
}