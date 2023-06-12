package com.happymeerkat.avocado.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.happymeerkat.avocado.domain.model.ListItem

@Composable
fun CompletedView(
    modifier: Modifier = Modifier,
    items: List<ListItem>
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth().height(500.dp).background(Color.Red)
    ) {
        items(items) {
            ItemView(item = it, navigateToDetails = { /*TODO*/ })
        }
    }
}

