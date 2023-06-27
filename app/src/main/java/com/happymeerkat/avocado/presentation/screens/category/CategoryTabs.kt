package com.happymeerkat.avocado.presentation.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.happymeerkat.avocado.domain.model.Category

@Composable
fun CategoryTabs(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    changeCurrentCategory: (category: Category) -> Unit,
    showEditDialog: () -> Unit,
    currentCategory: Category,
    showCreateNewCategoryModal: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(4f)
                .padding(vertical = 4.dp)
        ) {
            LazyRow {
                items(categories){it ->
                    Tab(
                        category = it,
                        focusOn = {changeCurrentCategory(it)},
                        isCurrent = it == currentCategory,
                        showEditDialog = {showEditDialog()}
                    )
                }
            }
        }
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { showCreateNewCategoryModal() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    }

}
