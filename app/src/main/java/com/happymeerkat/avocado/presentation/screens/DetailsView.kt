package com.happymeerkat.avocado.presentation.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsView(
    modifier: Modifier = Modifier.fillMaxSize(),
    title: String,
    description: String?,
    categoryId: Int,
    dateMade: Long,
    dateDue: Long?,
    timeDue: Long?,
    completed: Boolean,
    backToHome: () -> Unit
) {
    Scaffold(
        topBar = { DetailsTopAppBar(navigateUp = backToHome) }
    ) {it ->
        Column(
            modifier = modifier
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp, vertical = 4.dp)
            ) {
                
                OutlinedTextField(
                    modifier = Modifier.background(Color.White),
                    value = title,
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
                    detail = dateDue?.let { it1 -> LocalDate.ofEpochDay(it1).format(DateTimeFormatter.ofPattern("EEE, MMM dd")) },
                    onClick = { }
                )

                DetailCard(
                    name = "Time due",
                    icon = Icons.Default.AccessTime,
                    detail = timeDue?.let { it1 -> Instant.ofEpochSecond(it1).atZone(ZoneId.of("UTC")).toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a")) },
                    onClick = {}
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
                        if (description != null) {
                            Text(description)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    icon: ImageVector,
    name: String,
    detail: String?,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.padding(bottom = 12.dp)
    ) {
        Column(
            modifier = modifier.padding(8.dp)
        ) {
            Text(text = name, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Icon(imageVector = icon, contentDescription = null)
                Spacer(modifier = Modifier.width(5.dp))
                if (detail != null) {
                    Text(detail)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopAppBar(
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = { /*TODO*/ },
        navigationIcon = {
                IconButton(onClick = { navigateUp() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
        },

    )
}