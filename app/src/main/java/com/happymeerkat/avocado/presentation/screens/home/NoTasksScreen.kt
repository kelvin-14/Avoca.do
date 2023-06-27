package com.happymeerkat.avocado.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.happymeerkat.avocado.R
import com.happymeerkat.avocado.domain.model.Category

@Composable
fun NoTasksScreen(
    modifier: Modifier = Modifier,
    currentCategory: Category
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = currentCategory.name, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(30.dp))
        Image(painter = painterResource(id = R.drawable.relax), contentDescription = "")
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "nothing here for now :)",
            fontSize = 20.sp
        )
    }
}