package com.happymeerkat.avocado.domain.use_case

data class ListUseCases(
    val getItems: GetItems,
    val getItemById: GetItemById,
    val upsertItem: UpsertItem,
    val deleteCompletedTasks: DeleteCompletedTasks,
    val categoryGetAll: CategoryGetAll,
    val categoryUpsert: CategoryUpsert,
    val categoryDelete: CategoryDelete
)
