package com.happymeerkat.avocado.presentation.screens.dateTime

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.avocado.presentation.vm.EditItemVM
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimeDialog(
    timeDialogState: MaterialDialogState,
    viewModel: EditItemVM = hiltViewModel(),
) {
    var pickedTime by remember{ mutableStateOf(LocalTime.now()) }
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "Ok", onClick = {viewModel.setTimeDue(pickedTime)})
            negativeButton(text = "Cancel")
        }
    ) {

        timepicker(
            initialTime = LocalTime.now(),
            title = "Pick a TIME"
        ) {
            pickedTime = it
        }
    }
}