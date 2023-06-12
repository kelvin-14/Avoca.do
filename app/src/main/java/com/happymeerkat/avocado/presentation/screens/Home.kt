package com.happymeerkat.avocado.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.presentation.vm.EditItemVM
import com.happymeerkat.avocado.presentation.vm.MainVM


val mockCategories = listOf(
    Category("All"),
    Category("Shopping"),
    Category("Work"),
    Category("School"),
    Category("Church"),
    Category("Goals")
)

val mockListItems = listOf(
    ListItem(title = "mock task", description = "mock desc")
)

@Composable
fun Home(
    modifier: Modifier = Modifier,
    navigateToDetails: (title: String, description: String) -> Unit,
    viewModel: MainVM = hiltViewModel(),
    editVM: EditItemVM = hiltViewModel()
) {
    val state = viewModel.mainUIState.collectAsState().value
    var editState by remember{ mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
        ) {
            CategoryTabs(
                modifier = Modifier.fillMaxWidth(),
                categories = mockCategories,
                addCategory = {}
            )
            ListScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                listItems = state.listItems,
                toggleEditState = { editState = true },
                editState = editState,
                navigateToDetails = navigateToDetails
            )

            if(editState == false){
                BottomOptions(
                    toggleEditState = { editState = true },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }


        if(editState == true){
            NewItemEditor(
                modifier = modifier
                    .align(Alignment.BottomCenter),
                closeModal = { editState = false }
            )
        }


    }
}