package com.happymeerkat.avocado.presentation.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.presentation.vm.EditItemVM
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemView(
    modifier: Modifier = Modifier.fillMaxWidth(),
    item: ListItem,
    navigateToDetails: () -> Unit,
    editVM: EditItemVM = hiltViewModel()
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp),
        onClick = {  Log.d("SEE", item.toString());navigateToDetails()}
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = item.completed, onCheckedChange = {
                    editVM.updateItem(item.copy(completed = !item.completed))
                })
                Text(text = item.title )
            }
            if(item.dateDue != null) {
                Row(
                    modifier = Modifier.padding(start = 15.dp, bottom = 18.dp)
                ) {
                    Icon(modifier = Modifier.size(18.dp), imageVector = Icons.Default.CalendarMonth, contentDescription = "date due")
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = LocalDate.ofEpochDay(item.dateDue).format(DateTimeFormatter.ofPattern("EEE, MMM dd")),
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.padding(end = 20.dp))
                    if(item.timeDue != null) {
                        Icon(modifier = Modifier.size(18.dp), imageVector = Icons.Default.AccessTime, contentDescription = "time due")
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = Instant.ofEpochSecond(item.timeDue).atZone(ZoneId.of("UTC")).toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a")),
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }

        
    }
}
