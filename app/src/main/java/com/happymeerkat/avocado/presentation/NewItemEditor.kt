package com.happymeerkat.avocado.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction

@Composable
fun NewItemEditor(
    modifier: Modifier = Modifier,
    closeModal: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable { closeModal() }
            .background(Color.Gray.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
        ) {
            TextField(
                value = "",
                onValueChange = {},
                placeholder = {Text("enter task")},
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {closeModal()})
            )
        }
    }

}