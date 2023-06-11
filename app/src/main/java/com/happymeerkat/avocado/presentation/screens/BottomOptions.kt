package com.happymeerkat.avocado.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BottomOptions(
    modifier: Modifier = Modifier,
    toggleEditState: () -> Unit
) {

    Row(
        modifier = modifier.background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically

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
                .padding(end = 20.dp, bottom = 10.dp, top = 10.dp),
            onClick = { toggleEditState() }
        ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "")
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
        modifier = Modifier.padding(start = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = icon, contentDescription = "")
        Text(name)
    }
}