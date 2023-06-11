package com.happymeerkat.avocado.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.happymeerkat.avocado.domain.model.ListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemView(
    modifier: Modifier = Modifier.fillMaxWidth(),
    item: ListItem,
    changeCheckedState: () -> Unit,
    goToDetails: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp),
        onClick = { goToDetails() }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = true, onCheckedChange = {changeCheckedState()} )
            Text(text = item.title )
        }
        
    }
}
