package com.happymeerkat.avocado.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.happymeerkat.avocado.domain.ListItem

@Composable
fun ListItemView(
    modifier: Modifier = Modifier.fillMaxWidth(),
    item: ListItem
) {
    Column(
        modifier = modifier
    ) {
        Text(text = item.title )
        if(item.description != null) {
            Text(text = item.description)
        }
    }
}