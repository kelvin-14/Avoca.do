package com.happymeerkat.avocado.presentation.screens

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
import androidx.compose.material.icons.filled.Delete
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
    addCategory: () -> Unit,
    changeCurrentCategory: (category: Category) -> Unit,
    currentCategory: Category
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
                        onClick = {changeCurrentCategory(it)},
                        isCurrent = it == currentCategory
                    )
                }
            }
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "")
            }
        }
    }

}

@Composable
fun Tab(
    category: Category,
    onClick: () -> Unit,
    isCurrent: Boolean
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .clickable { onClick() }
    ) {
        Text(
            modifier = Modifier
                .background(if(isCurrent) Color.Yellow else Color.White)
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ),
            text = category.name
        )
    }
}