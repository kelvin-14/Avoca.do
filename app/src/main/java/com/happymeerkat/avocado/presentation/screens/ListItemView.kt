package com.happymeerkat.avocado.presentation.screens

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.presentation.vm.EditItemVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemView(
    modifier: Modifier = Modifier.fillMaxWidth(),
    item: ListItem,
    navigateToDetails: () -> Unit,
    editVM: EditItemVM = hiltViewModel()
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp),
        onClick = { navigateToDetails()}
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = item.completed, onCheckedChange = {
                editVM.updateItem(item.copy(completed = !item.completed))
            })
            Text(text = item.title )
        }
        
    }
}
