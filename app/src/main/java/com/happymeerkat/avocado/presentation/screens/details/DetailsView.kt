package com.happymeerkat.avocado.presentation.screens.details

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.avocado.presentation.screens.dialog.DateDialog
import com.happymeerkat.avocado.presentation.screens.dialog.TimeDialog
import com.happymeerkat.avocado.presentation.vm.EditItemVM
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsView(
    modifier: Modifier = Modifier.fillMaxSize(),
    backToHome: () -> Unit,
    viewModel: EditItemVM = hiltViewModel()
) {

    val state = viewModel.itemUIState.collectAsState().value
    val context = LocalContext.current

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()


    Scaffold(
        topBar = {
            DetailsTopAppBar(
                modifier = Modifier.fillMaxWidth().background(Color.Red),
                navigateUp = backToHome,
                updateItem = {viewModel.updateItem(null, context)}
            )
        }
    ) {it ->
        Column(
            modifier = modifier
                .padding(it)
        ) {
            BackHandler(enabled = true) {
                backToHome()
                viewModel.updateItem(null, context)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 22.dp)
            ) {
                BasicTextField(
                    modifier = Modifier
                        .padding(top = 6.dp, bottom = 24.dp)
                        .wrapContentWidth(),
                    value = state.title,
                    onValueChange = { viewModel.editTitle(it) },
                    textStyle = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.colorScheme.onPrimary),
                    maxLines = 5,
                    singleLine = false,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 5.dp),
                        text = "Notes",
                        fontSize = 20.sp
                    )

                    TextField(
                        value = state.description ?: "",
                        onValueChange = { viewModel.editDescription(it) },
                        maxLines = 10,
                        placeholder = {Text("Write some notes...")},
                        colors = textFieldColors(
                            focusedContainerColor = MaterialTheme.colorScheme.background,
                            unfocusedContainerColor = MaterialTheme.colorScheme.background,
                            focusedIndicatorColor = MaterialTheme.colorScheme.background,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
                            cursorColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimary),
                        enabled = true
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                DetailCard(
                    name = "Due date",
                    icon = Icons.Default.CalendarMonth,
                    detail = if (state.dateDue != null) viewModel.getFormattedDate(state.dateDue) else "Tap to set date",
                    onClick = { dateDialogState.show() }
                )

                Spacer(modifier = Modifier.height(24.dp))

                DetailCard(
                    name = "Time due",
                    icon = Icons.Default.AccessTime,
                    detail = if (state.timeDue != null) viewModel.getFormattedTime(state.timeDue)
                    else (if (state.dateDue != null) "Tap to set time" else "set date before setting time"),
                    onClick = { if (state.dateDue != null) timeDialogState.show() else dateDialogState.show() }
                )


                DateDialog(dateDialogState = dateDialogState, openTimeDialog = {timeDialogState.show()})
                TimeDialog(timeDialogState = timeDialogState)
            }
        }
    }
}





