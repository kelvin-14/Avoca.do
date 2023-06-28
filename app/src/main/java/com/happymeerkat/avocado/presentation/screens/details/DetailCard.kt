package com.happymeerkat.avocado.presentation.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    icon: ImageVector,
    name: String,
    detail: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
    ) {
        Text(text = name, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Icon(imageVector = icon, contentDescription = null)
            Spacer(modifier = Modifier.width(5.dp))
            Text(detail)
        }
    }
}