package com.happymeerkat.avocado.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.happymeerkat.avocado.domain.model.ListItem

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    listItems: List<ListItem>,
    toggleEditState: () -> Unit,
    editState: Boolean
) {
    Box(modifier = modifier) {
        LazyColumn(
            modifier = modifier
        ) {
            items(listItems) {listItem ->
                ListItemView(
                    item = listItem,
                    changeCheckedState = {},
                    goToDetails = {}
                )
            }
        }
    }
}

