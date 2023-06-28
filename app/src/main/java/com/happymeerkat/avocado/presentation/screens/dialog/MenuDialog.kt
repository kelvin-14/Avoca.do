package com.happymeerkat.avocado.presentation.screens.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MenuDialog(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    toggleExpandedState: () -> Unit,
    showCreateNewCategoryModal: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.1f))
            .wrapContentSize(Alignment.TopEnd)
            .padding(end = 38.dp, top = 35.dp)

    ) {
        DropdownMenu(
            modifier = Modifier.background(Color(0xfffffded)),
            expanded = expanded,
            onDismissRequest = { toggleExpandedState() }
        ) {
            MenuItem(name = "new list group", icon = Icons.Default.Add, closeMenu = { toggleExpandedState() }, onClick = { showCreateNewCategoryModal() })
            MenuItem(name = "Tips", icon = Icons.Default.TipsAndUpdates, closeMenu = { toggleExpandedState() }, onClick = {})
            MenuItem(name = "Dark Mode", icon = Icons.Default.DarkMode, closeMenu = { toggleExpandedState() }, onClick = {})

        }
    }
}

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    name: String,
    icon: ImageVector,
    closeMenu: () -> Unit,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        leadingIcon = { Icon(imageVector = icon, contentDescription = "", tint = MaterialTheme.colorScheme.onPrimary)},
        text = { Text(name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onPrimary) },
        onClick = { closeMenu(); onClick() }
    )
}