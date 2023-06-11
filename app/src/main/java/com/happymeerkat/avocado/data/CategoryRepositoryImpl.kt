package com.happymeerkat.avocado.data

import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dao:CategoryDao
): CategoryRepository {
    override fun getAllCategories(): Flow<List<Category>> = dao.getAllCategories()

    override suspend fun upsertCategory(category: Category) = dao.upsertCategory(category)

    override suspend fun deleteCategory(category: Category) = dao.deleteCategory(category)
}