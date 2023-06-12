package com.happymeerkat.avocado.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailsView(
    modifier: Modifier = Modifier.fillMaxSize(),
    title: String,
    description: String,
    backToHome: () -> Unit
) {
    Scaffold(
        topBar = { DetailsTopAppBar(navigateUp = backToHome) }
    ) { it ->
        Column(
            modifier = modifier.padding(it)
        ) {
            Text(text = title)
            Text(description)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopAppBar(
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = { /*TODO*/ },
        navigationIcon = {
                IconButton(onClick = { navigateUp() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
        },

    )
}