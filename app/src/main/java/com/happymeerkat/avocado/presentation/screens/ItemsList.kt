package com.happymeerkat.avocado.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.happymeerkat.avocado.domain.model.ListItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemsList(
    modifier: Modifier = Modifier,
    listItems: List<ListItem>,
    completedItems: List<ListItem>,
    navigateToDetails: (id : Int) -> Unit,
    showDeleteCompletedItemsDialog: () -> Unit
) {
    Box(modifier = modifier) {
        if((listItems.isEmpty()) and (completedItems.isEmpty())) {
            NoTasksScreen(
                modifier = Modifier.matchParentSize()
            )
        }
        else{
            TasksExistScreen(
                modifier = Modifier.matchParentSize(),
                listItems = listItems,
                completedItems = completedItems,
                navigateToDetails = navigateToDetails,
                showDeleteCompletedItemsDialog = showDeleteCompletedItemsDialog
            )
        }

    }
}
