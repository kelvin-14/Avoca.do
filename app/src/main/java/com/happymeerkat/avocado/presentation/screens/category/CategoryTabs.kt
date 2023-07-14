package com.happymeerkat.avocado.presentation.screens.category

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.happymeerkat.avocado.domain.model.Category
import kotlinx.coroutines.launch

@Composable
fun CategoryTabs(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    changeCurrentCategory: (category: Category) -> Unit,
    currentCategory: Category,
    showEditDialog: () -> Unit,
    focusIndex: Int
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(8f)
        ) {
            LazyRow(
                state = listState
            ) {
                items(categories){it ->
                    LaunchedEffect(focusIndex) {
                        coroutineScope.launch {
                            listState.animateScrollToItem(focusIndex)
                        }
                    }
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
