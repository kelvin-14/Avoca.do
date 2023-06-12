package com.happymeerkat.avocado.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.model.ListItem

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    listItems: List<ListItem>,
    completedItems: List<ListItem>,
    toggleEditState: () -> Unit,
    editState: Boolean,
    navigateToDetails: (title: String, description: String) -> Unit,
    currentCategory: Category
) {
    Box(modifier = modifier) {
        if((listItems.isEmpty()) and (completedItems.isEmpty())) {
            NoTasksScreen(
                modifier = Modifier.matchParentSize()
            )
        }
        else{
            LazyColumn(
                modifier = Modifier.matchParentSize()
            ) {
                listItems.forEach{
                    item {
                        ItemView(item = it, navigateToDetails = { navigateToDetails(it.title, it.description ?: "") })
                    }
                }
                item {
                    Text(text = "Completed")
                }
                completedItems.forEach{
                    item {
                        ItemView(item = it, navigateToDetails = { /*TODO*/ })
                    }
                }
            }

        }

    }
}
