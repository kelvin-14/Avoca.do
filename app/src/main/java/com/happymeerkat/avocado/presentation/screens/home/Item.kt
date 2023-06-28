package com.happymeerkat.avocado.presentation.screens.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.presentation.vm.EditItemVM
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Item(
    modifier: Modifier = Modifier.fillMaxWidth(),
    item: ListItem,
    navigateToDetails: () -> Unit,
    viewModel: EditItemVM = hiltViewModel()
) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .padding(vertical = 4.dp)
            .combinedClickable (
                onClick = {navigateToDetails()},
                onLongClick = { Log.d("LONG CLICK", "long click") }
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Checkbox(
                    checked = item.completed,
                    onCheckedChange = {viewModel.markCompleted(item.copy(completed = !item.completed), context)},
                    modifier = Modifier.fillMaxSize(),
                )
            }

            Column(
                modifier = Modifier
                    .weight(6f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
                        text = item.title,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                if(item.dateDue != null) {
                    Row(
                        modifier = Modifier
                            .padding(bottom = 6.dp)
                            .fillMaxWidth()
                    ) {
                        TimeDetail(
                            modifier = Modifier.weight(1f),
                            icon = Icons.Default.CalendarMonth,
                            text = viewModel.getFormattedDate(item.dateDue)
                        )
                        Spacer(modifier = Modifier.width(25.dp))
                        if(item.timeDue != null) {
                            TimeDetail(
                                modifier = Modifier.weight(1f),
                                icon = Icons.Default.AccessTime,
                                text = viewModel.getFormattedTime(item.timeDue)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TimeDetail(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
    description: String = ""
) {
    Row {
        Icon(modifier = Modifier.size(18.dp), imageVector = icon, contentDescription = description)
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}