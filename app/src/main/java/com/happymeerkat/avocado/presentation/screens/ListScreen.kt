package com.happymeerkat.avocado.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
    var visible by remember { mutableStateOf(true) }
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
                    if(completedItems.isNotEmpty()){
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Completed")
                            IconButton(onClick = { visible = !visible}) {
                                Icon(
                                    imageVector = if(visible) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowLeft,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }

                item {

                        completedItems.forEach{
                            AnimatedVisibility(visible = visible) {
                            ItemView(item = it, navigateToDetails = { /*TODO*/ })
                        }
                    }
                }

            }

        }

    }
}
