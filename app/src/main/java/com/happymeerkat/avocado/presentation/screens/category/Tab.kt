package com.happymeerkat.avocado.presentation.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
            .padding(horizontal = 4.dp, vertical = 10.dp)
            .clickable {
                if((isCurrent) and (category.name != "All")) showEditDialog() else focusOn()
            }
            .clip(RoundedCornerShape(10.dp))

    ) {
        Text(
            modifier = Modifier
                .background(if(isCurrent) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.background)
                .padding(
                    horizontal = 15.dp,
                    vertical = 6.dp
                )
            ,
            text = category.name,
            color = if(isCurrent) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}