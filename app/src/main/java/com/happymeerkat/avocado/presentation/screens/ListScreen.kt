package com.happymeerkat.avocado.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.happymeerkat.avocado.R
import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.model.ListItem

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    listItems: List<ListItem>,
    toggleEditState: () -> Unit,
    editState: Boolean,
    navigateToDetails: (title: String, description: String) -> Unit,
    currentCategory: Category
) {
    Box(modifier = modifier) {
        if(listItems.isEmpty()) {
            NoTasksScreen(
                modifier = Modifier.matchParentSize()
            )
        }
        else{
            LazyColumn(
                modifier = modifier
            ) {
                items(listItems) {listItem ->
                    AnimatedVisibility(visible = !listItem.completed) {
                        ItemView(
                            item = listItem,
                            navigateToDetails = {navigateToDetails(listItem.title, listItem.description ?: "")},
                        )
                    }
                }
            }
        }

    }
}
