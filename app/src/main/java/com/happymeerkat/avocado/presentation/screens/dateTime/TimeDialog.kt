package com.happymeerkat.avocado.presentation.screens.dateTime

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeDialog(
    timeDialogState: MaterialDialogState,
    changePickedTime: (newTime: LocalTime) -> Unit
) {
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "Ok")
            negativeButton(text = "Cancel")
        }
    ) {

        timepicker(
            initialTime = LocalTime.now(),
            title = "Pick a TIME"
        ) {
            changePickedTime(it)
        }
    }
}