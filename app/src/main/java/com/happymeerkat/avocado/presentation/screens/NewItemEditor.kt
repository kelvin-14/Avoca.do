package com.happymeerkat.avocado.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.avocado.presentation.vm.EditItemVM

@Composable
fun NewItemEditor(
    modifier: Modifier = Modifier,
    closeModal: () -> Unit,
    viewModel: EditItemVM = hiltViewModel()
) {
    val state = viewModel.itemUIState.collectAsState().value

    Box(
        modifier = modifier
            .clickable { closeModal() }
            .background(Color.Gray.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.title,
                    onValueChange = {viewModel.editTitle(it)},
                    placeholder = {Text("enter task")},
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        closeModalAndSave(closeModal = closeModal, save = {viewModel.upsertListItem()})
                    })
                )
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "")
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "")
            }

        }
    }

}

fun closeModalAndSave(
    closeModal: () -> Unit,
    save: () -> Unit
) {
    closeModal()
    save()
}