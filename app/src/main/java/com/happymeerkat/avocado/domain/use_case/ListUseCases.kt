package com.happymeerkat.avocado.domain.use_case

data class ListUseCases(
    val getItems: GetItems,
    val upsertItem: UpsertItem,
    val deleteCompletedTasks: DeleteCompletedTasks,
    val getAllCategories: GetCategories,
    val createCategory: CreateCategory,
    val deleteCategory: DeleteCategory
)
