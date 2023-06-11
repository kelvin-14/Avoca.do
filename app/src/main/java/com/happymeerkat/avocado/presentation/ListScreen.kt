package com.happymeerkat.avocado.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.happymeerkat.avocado.domain.ListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    listItems: List<ListItem>,
    toggleEditState: () -> Unit,
    editState: Boolean
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier.matchParentSize()
        ) {
            items(listItems) {listItem ->
                ListItemView(
                    item = listItem,
                    changeCheckedState = {},
                    goToDetails = {}
                )
            }
        }
        if(editState == false){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),

            ) {

                QuickFeature(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Search,
                    name = "search",
                    onClick = {}
                )
                QuickFeature(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.SortByAlpha,
                    name = "sort by",
                    onClick = {}
                )

                QuickFeature(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Check,
                    name = "select",
                    onClick = {}
                )
                Column(
                    modifier = Modifier.weight(2f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                }
                FloatingActionButton(
                    modifier = Modifier
                        .padding(end = 20.dp, bottom = 20.dp),
                    onClick = { toggleEditState() }
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                }
            }
        }

    }
}

@Composable
fun QuickFeature(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    name: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = icon, contentDescription = "")
        Text(name)
    }
}