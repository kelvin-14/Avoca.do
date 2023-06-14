package com.happymeerkat.avocado.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    var editCategory by remember{ mutableStateOf(false) }
    var createCategory by remember{ mutableStateOf(false) }
    var deleteCategory by remember{ mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            CategoryTabs(
                modifier = Modifier.fillMaxWidth(),
                categories = state.categories,
                changeCurrentCategory = {category -> viewModel.changeCurrentCategory(category)},
                currentCategory = state.currentCategory,
                showEditDialog = {editCategory = true},
                showCreateNewCategoryModal = {createCategory = true}
            )

            ListScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                listItems = if(state.currentCategory.name == "All") state.listItems.filter { !it.completed }
                else state.listItems.filter { (!it.completed) and (it.category == state.currentCategory.name) },
                toggleEditState = { editState = true },
                editState = editState,
                navigateToDetails = navigateToDetails,
                currentCategory = state.currentCategory,
                completedItems = if(state.currentCategory.name == "All") state.listItems.filter { it.completed }
                else state.listItems.filter { (it.completed) and (it.category == state.currentCategory.name) },
                deleteCompletedItems = {viewModel.deleteCompletedTasks()}
            )

            if(!editState){
                BottomOptions(
                    toggleEditState = { editState = true },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }


        if(editState){
            NewItemEditor(
                modifier = modifier
                    .align(Alignment.BottomCenter),
                closeModal = { editState = false },
                currentCategory = state.currentCategory
            )
        }

        if(editCategory) {
            EditCategoryDialog(
                closeModal = {editCategory = false},
                category = state.currentCategory,
                deleteCurrentCategory = {deleteCategory = true}
            )
        }

        if(createCategory) {
            CreateCategoryDialog(
                createCategory = { category: Category -> viewModel.createNewCategory(category) },
                closeModal = {createCategory = false}
            )
        }
        
        if(deleteCategory) {
            ConfirmationDialog(
                title = "Delete Category \"${state.currentCategory.name}\" ?",
                message = "Deleting this category will delete all the tasks associated with it as well",
                functionToRun = { viewModel.deleteCurrentCategory()},
                closeModal = { deleteCategory = false }
            )
        }


    }
}