package com.happymeerkat.avocado.presentation.screens.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.happymeerkat.avocado.domain.model.Category

@Composable
fun CategoryTabs(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    changeCurrentCategory: (category: Category) -> Unit,
    currentCategory: Category,
    showEditDialog: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(8f)
        ) {
            LazyRow {
                items(categories){it ->
                    Tab(
                        category = it,
                        focusOn = { changeCurrentCategory(it) },
                        isCurrent = it == currentCategory,
                        showEditDialog = { showEditDialog() }
                    )
                }
            }
        }
    }

}
