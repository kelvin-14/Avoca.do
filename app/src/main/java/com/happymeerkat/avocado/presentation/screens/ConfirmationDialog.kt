package com.happymeerkat.avocado.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    functionToRun: () -> Unit,
    closeModal: () -> Unit
) {
    Dialog(onDismissRequest = { closeModal() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(title, fontWeight = FontWeight.Bold)
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(message)
                }
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = { closeModal() }) {
                        Text(text = "CANCEL", color = Color.Red)
                    }
                    TextButton(onClick = { deleteThenClose(functionToRun, closeModal) }) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }
}

fun deleteThenClose(
    func: () -> Unit,
    close: () -> Unit
) {
    func()
    close()
}