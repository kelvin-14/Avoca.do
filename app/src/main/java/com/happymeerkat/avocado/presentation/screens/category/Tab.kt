package com.happymeerkat.avocado.presentation.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.happymeerkat.avocado.domain.model.Category


@Composable
fun Tab(
    category: Category,
    showEditDialog: () -> Unit,
    focusOn: () -> Unit,
    isCurrent: Boolean
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .clickable {
                if((isCurrent) and (category.name != "All")) showEditDialog() else focusOn()
            }
    ) {
        Text(
            modifier = Modifier
                .background(if(isCurrent) Color.Yellow else Color.White)
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ),
            text = category.name,
            color = Color.Black,
            style = MaterialTheme.typography.labelSmall
        )
    }
}