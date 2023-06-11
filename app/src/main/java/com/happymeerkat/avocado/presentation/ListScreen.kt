package com.happymeerkat.avocado.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
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

