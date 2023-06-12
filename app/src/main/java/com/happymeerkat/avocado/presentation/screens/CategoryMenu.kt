package com.happymeerkat.avocado.presentation.screens

import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.happymeerkat.avocado.domain.model.Category

@Composable
fun CategoryMenu(
    categories: List<Category>,
    expanded: Boolean,
    dismiss: () -> Unit
) {

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { dismiss() }
    ) {
        categories.forEach {
            DropdownMenuItem(
                text = {  Text(it.name) },
                onClick = { /* Handle refresh! */ }
            )
            Divider()
        }
    }
}