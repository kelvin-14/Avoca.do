package com.happymeerkat.avocado.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ListItemDetails(
    modifier: Modifier = Modifier.fillMaxSize(),
    title: String,
    description: String
) {
    Column(
        modifier = modifier
    ) {
        Text(text = title)
        Text(description)
    }
}