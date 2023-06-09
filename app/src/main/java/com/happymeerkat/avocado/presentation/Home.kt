package com.happymeerkat.avocado.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.happymeerkat.avocado.domain.Category
import com.happymeerkat.avocado.domain.ListItem


val mockCategories = listOf(
    Category("All"),
    Category("Shopping"),
    Category("Work"),
    Category("School"),
    Category("Church"),
    Category("Goals")
)

val mockItems = listOf(
    ListItem("task1", "description"),
    ListItem("task2", "description2")
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
                modifier = Modifier,
                listItems = mockItems,
                toggleEditState = { editState = true }
            )
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