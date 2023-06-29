package com.happymeerkat.avocado.presentation.screens.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun InformationalDialog(
    title: String?,
    message: String,
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
                    .background(MaterialTheme.colorScheme.background)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(title != null) {
                    Text(
                        modifier = Modifier.padding(vertical = 12.dp),
                        text = title,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(message, lineHeight = 30.sp)
                }
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { closeModal() }) {
                        Text(text = "OK", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }
    }
}