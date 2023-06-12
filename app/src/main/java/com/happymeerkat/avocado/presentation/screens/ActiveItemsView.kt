package com.happymeerkat.avocado.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.happymeerkat.avocado.domain.model.ListItem

@Composable
fun ActiveItemsView(
    modifier: Modifier = Modifier,
    navigateToDetails: (title: String, description: String) -> Unit,
    listItems: List<ListItem>
) {
    Column(
        modifier = modifier
    ) {
        listItems.forEach{listItem ->
                ItemView(
                    item = listItem,
                    navigateToDetails = {navigateToDetails(listItem.title, listItem.description ?: "")},
                )
            }
    }
}