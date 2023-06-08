package com.happymeerkat.avocado.presentation

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.happymeerkat.avocado.domain.Category

@Composable
fun CategoryTabs(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    addCategory: () -> Unit
) {
    Row(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.weight(4f)
                .padding(vertical = 4.dp)
        ) {
            LazyRow {
                items(categories){it ->
                    Tab(category = it, onClick = {})
                }
            }
        }
        Column(
            modifier = Modifier.weight(2f)
        ) {
            TextButton(
                onClick = {}
            ) {
                Text(text = "Add category")
            }
        }
    }

}

@Composable
fun Tab(
    category: Category,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .clickable { onClick },
    ) {
        Text(
            modifier = Modifier
                .padding( horizontal = 8.dp),
            text = category.name
        )
    }
}