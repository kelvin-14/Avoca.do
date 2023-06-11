package com.happymeerkat.avocado.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.happymeerkat.avocado.domain.model.ListItem

@Composable
fun ListItemDetails(
    modifier: Modifier = Modifier.fillMaxSize(),
    listItem: ListItem
) {
    Column(
        modifier = modifier
    ) {
        if(listItem.description != null){
            Text(listItem.description)
        }
    }
}