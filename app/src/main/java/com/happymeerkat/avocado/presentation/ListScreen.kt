package com.happymeerkat.avocado.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.happymeerkat.avocado.domain.ListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    listItems: List<ListItem>,
    toggleEditState: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier.matchParentSize()
        ) {
            items(listItems) {listItem ->
                ListItemView(item = listItem)
            }
        }

        FloatingActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = { toggleEditState() }
        ) {

        }
    }

}