package com.happymeerkat.avocado.domain.repository

import com.happymeerkat.avocado.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(): Flow<List<Category>>

    suspend fun upsertCategory(category: Category)
    suspend fun deleteCategory(category: Category)
}