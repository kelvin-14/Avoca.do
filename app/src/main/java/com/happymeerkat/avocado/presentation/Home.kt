package com.happymeerkat.avocado.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.model.ListItem


val mockCategories = listOf(
    Category("All"),
    Category("Shopping"),
    Category("Work"),
    Category("School"),
    Category("Church"),
    Category("Goals")
)

val mockItems = listOf(
    ListItem(title = "task1", description = "description"),
    ListItem(title = "task2", description = "description2"),ListItem(title = "task1", description = "description"),
    ListItem(title = "task2", description = "description2"),ListItem(title = "task1", description = "description"),
    ListItem(title = "task2", description = "description2"),ListItem(title = "task1", description = "description"),
    ListItem(title = "task2", description = "description2"),ListItem(title = "task1", description = "description"),
    ListItem(title = "task2", description = "description2"),ListItem(title = "task1", description = "description"),
    ListItem(title = "task2", description = "description2"),ListItem(title = "task1", description = "description"),
    ListItem(title = "task2", description = "description2"),ListItem(title = "task1", description = "description"),
    ListItem(title = "last task", description = "description2"),
)
@Composable
fun Home(
    modifier: Modifier = Modifier
) {
    var editState by remember{ mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
        ) {
            CategoryTabs(
                modifier = Modifier.fillMaxWidth(),
                categories = mockCategories,
                addCategory = {}
            )
            ListScreen(
                modifier = Modifier.fillMaxWidth().weight(1f),
                listItems = mockItems,
                toggleEditState = { editState = true },
                editState = editState
            )

            if(editState == false){
                BottomOptions(
                    toggleEditState = { editState = true },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }


        if(editState == true){
            NewItemEditor(
                modifier = modifier
                    .align(Alignment.BottomCenter),
                closeModal = { editState = false }
            )
        }


    }
}