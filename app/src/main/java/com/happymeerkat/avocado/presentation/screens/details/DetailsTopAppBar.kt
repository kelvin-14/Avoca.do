package com.happymeerkat.avocado.presentation.screens.details

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopAppBar(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    updateItem: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = { /*TODO*/ },
        navigationIcon = {
            IconButton(onClick = { navigateUp(); updateItem() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )

    )
}