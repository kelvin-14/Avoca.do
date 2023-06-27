package com.happymeerkat.avocado.presentation.screens.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.happymeerkat.avocado.domain.model.Category

@Composable
fun EditCategoryDialog(
    modifier: Modifier = Modifier,
    closeModal: () -> Unit,
    category: Category,
    showConfirmationDialog: () -> Unit,
    editCategoryName: (newName: String) -> Unit
) {
    var newName by remember { mutableStateOf(category.name) }

    Dialog(onDismissRequest = { closeModal() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("EDIT CATEGORY", fontWeight = FontWeight.Bold)
                Row {
                    TextField(value = newName, onValueChange = {newName = it})
                }
                Row(

                ) {
                    TextButton(onClick = { showConfirmationDialog(); closeModal() }) {
                        Text(text = "DELETE CATEGORY", color = Color.Red)
                    }
                    TextButton(onClick = { editCategoryName(newName); closeModal() }) {
                        Text(text = "SAVE NEW NAME")
                    }
                }
            }
        }
    }
}

