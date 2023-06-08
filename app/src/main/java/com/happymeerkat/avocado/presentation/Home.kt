package com.happymeerkat.avocado.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.happymeerkat.avocado.domain.Category


val mockCategories = listOf(
    Category("All"),
    Category("Shopping"),
    Category("Work"),
    Category("School"),
    Category("Church"),
    Category("Goals")
)
@Composable
fun Home(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        CategoryTabs(
            modifier = Modifier.fillMaxWidth(),
            categories = mockCategories,
            addCategory = {}
        )
        ListScreen(
            modifier = Modifier.fillMaxSize(),
            onClick = {}
        )
    }
}