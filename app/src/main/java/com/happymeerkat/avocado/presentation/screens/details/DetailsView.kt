package com.happymeerkat.avocado.presentation.screens.details

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.avocado.presentation.screens.dialog.DateDialog
import com.happymeerkat.avocado.presentation.screens.dialog.TimeDialog
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
    val context = LocalContext.current

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()


    Scaffold(
        topBar = { DetailsTopAppBar(navigateUp = backToHome, updateItem = {viewModel.updateItem(null, context)}) }
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
                    .padding(horizontal = 18.dp, vertical = 4.dp)
            ) {
                BasicTextField(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    value = state.title,
                    onValueChange = { viewModel.editTitle(it) },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.headlineLarge
                )
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 5.dp),
                        text = "Notes",
                        fontSize = 20.sp
                    )

//                    BasicTextField(
//                        modifier = Modifier,
//                        value = state.description ?: "",
//                        onValueChange = { viewModel.editDescription(it) },
//                        textStyle = MaterialTheme.typography.bodyLarge,
//                    )
                    OutlinedTextField(value = state.description ?: "", onValueChange = { viewModel.editDescription(it) }, maxLines = 10)
                }

                Spacer(modifier = Modifier.height(12.dp))
                DetailCard(
                    name = "Due date",
                    icon = Icons.Default.CalendarMonth,
                    detail = if (state.dateDue != null) viewModel.getFormattedDate(state.dateDue) else "Tap to set date",
                    onClick = { dateDialogState.show() }
                )
                Spacer(modifier = Modifier.height(12.dp))
                DetailCard(
                    name = "Time due",
                    icon = Icons.Default.AccessTime,
                    detail = if (state.timeDue != null) viewModel.getFormattedTime(state.timeDue)
                    else (if (state.dateDue != null) "Tap to set time" else "set date before setting time"),
                    onClick = { if (state.dateDue != null) timeDialogState.show() else dateDialogState.show() }
                )
                Spacer(modifier = Modifier.height(12.dp))


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
        Column(
            modifier = modifier
                .padding(8.dp)
                .clickable { onClick() }
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

