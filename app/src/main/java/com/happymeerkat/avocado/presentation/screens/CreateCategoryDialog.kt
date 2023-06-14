package com.happymeerkat.avocado.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun CreateCategoryDialog(
    modifier: Modifier = Modifier,
    createCategory: (newCategory: Category) -> Unit,
    closeModal: () -> Unit
) {
    var newCategoryName by remember { mutableStateOf("") }
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

                Text("NEW CATEGORY", fontWeight = FontWeight.Bold)
                Row {
                    TextField(value = newCategoryName, onValueChange = {newCategoryName = it}, placeholder = {Text(text = "enter new category name")})
                }
                Row(
                ) {
                    TextButton(onClick = {closeModal() }) {
                        Text(text = "CANCEL", color = Color.Red)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = { createCategoryExt(
                        create = {createCategory(Category(name = newCategoryName))},
                        closeModal = closeModal
                    ) }) {
                        Text(text = "CREATE")
                    }
                }
            }
        }
    }
}

fun createCategoryExt(
    create: () -> Unit,
    closeModal: () -> Unit
) {
    create()
    closeModal()

}