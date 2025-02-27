package com.happymeerkat.avocado.presentation.screens.home

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.unit.dp
import com.happymeerkat.avocado.domain.model.ListItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TasksExistScreen(
    modifier: Modifier = Modifier,
    listItems: List<ListItem>,
    completedItems: List<ListItem>,
    navigateToDetails: (id: Int) -> Unit,
    showDeleteCompletedItemsDialog: () -> Unit,
    updateWidget: (Context) -> Unit
) {

    var visible by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
    ) {
        listItems.forEach{
            item {
                Item(
                    item = it,
                    navigateToDetails = { navigateToDetails(it.id!!) }
                ) { context: Context -> updateWidget(context) }
            }
        }
        item {
            if(completedItems.isNotEmpty()){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(modifier = Modifier.padding(start = 8.dp), text = "Completed")
                    IconButton(onClick = { visible = !visible}) {
                        Icon(
                            imageVector = if(visible) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowLeft,
                            contentDescription = ""
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
//                    IconButton(onClick = {showDeleteCompletedItemsDialog()}) {
//                        Icon(
//                            imageVector = Icons.Default.Delete,
//                            contentDescription = ""
//                        )
//                    }
                }
            }
        }

        item {

            completedItems.forEach{
                AnimatedVisibility(visible = visible) {
                    Item(
                        item = it,
                        navigateToDetails = { navigateToDetails(it.id!!) },
                        updateWidget = {context: Context -> updateWidget(context)}
                    )
                }
            }
        }

    }
}