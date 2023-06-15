package com.happymeerkat.avocado.presentation.screens.dateTime

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateDialog(
    dateDialogState: MaterialDialogState,
    changePickedDate: (newDate: LocalDate) -> Unit,
    openTimeDialog: () -> Unit
) {
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "Ok")
            negativeButton(text = "Cancel")
        }
    ) {

        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
            allowedDateValidator = { it.dayOfMonth % 2 == 1 }
        ) {
            changePickedDate(it)
        }
        TextButton(onClick = { openTimeDialog() }) {
            Text("Set time as well", fontSize = 20.sp)
        }
    }
}