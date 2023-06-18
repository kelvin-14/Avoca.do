package com.happymeerkat.avocado.presentation.screens

import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.avocado.presentation.screens.dateTime.DateDialog
import com.happymeerkat.avocado.presentation.screens.dateTime.TimeDialog
import com.happymeerkat.avocado.presentation.vm.EditItemVM
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsView(
    modifier: Modifier = Modifier.fillMaxSize(),
    backToHome: () -> Unit,
    viewModel: EditItemVM = hiltViewModel()
) {

    val state = viewModel.itemUIState.collectAsState().value

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()


    Scaffold(
        topBar = { DetailsTopAppBar(navigateUp = backToHome, updateItem = {viewModel.updateItem(null)}) }
    ) {it ->
        Column(
            modifier = modifier
                .padding(it)
        ) {
            BackHandler(enabled = true) {
                backToHome()
                viewModel.updateItem(null)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp, vertical = 4.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier.background(Color.White),
                    value = state.title,
                    onValueChange = {},
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))
                DetailCard(
                    name = "Due date",
                    icon = Icons.Default.CalendarMonth,
                    detail = if(state.dateDue!!.toInt() != 0) LocalDate.ofEpochDay(state.dateDue).format(DateTimeFormatter.ofPattern("EEE, MMM dd"))  else  "Tap to set date",
                    onClick = { dateDialogState.show() }
                )

                DetailCard(
                    name = "Time due",
                    icon = Icons.Default.AccessTime,
                    detail = if(state.timeDue!!.toInt() != 0) Instant.ofEpochSecond(state.timeDue).atZone(ZoneId.of("UTC")).toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"))  else "Tap to set time",
                    onClick = { timeDialogState.show() }
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = RoundedCornerShape(5.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            text = "Description",
                            fontSize = 20.sp
                        )
                        if (state.description != "") {
                            Text(state.description)
                        }
                    }
                }
                DateDialog(dateDialogState = dateDialogState, openTimeDialog = {timeDialogState.show()})
                TimeDialog(timeDialogState = timeDialogState)
            }
        }
    }
}

@Composable
fun DetailCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    icon: ImageVector,
    name: String,
    detail: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(bottom = 12.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = modifier.padding(8.dp)
        ) {
            Text(text = name, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Icon(imageVector = icon, contentDescription = null)
                Spacer(modifier = Modifier.width(5.dp))
                Text(detail)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopAppBar(
    navigateUp: () -> Unit,
    updateItem: () -> Unit
) {
    TopAppBar(
        title = { /*TODO*/ },
        navigationIcon = {
                IconButton(onClick = { navigateUp(); updateItem() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
        },

    )
}