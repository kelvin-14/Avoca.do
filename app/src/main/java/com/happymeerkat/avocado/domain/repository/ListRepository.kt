package com.happymeerkat.avocado.domain.repository

import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.model.ListItem
import kotlinx.coroutines.flow.Flow

interface ListRepository {
    fun getAllListItems(): Flow<List<ListItem>>
    //fun getAllListItemsByCategory(): Flow<List<ListItem>>

    suspend fun upsertItem(listItem: ListItem)
    suspend fun deleteItem(listItem: ListItem)

    suspend fun deleteAllCompleted()

    suspend fun deleteAllCompletedByCategory(category: String)
}