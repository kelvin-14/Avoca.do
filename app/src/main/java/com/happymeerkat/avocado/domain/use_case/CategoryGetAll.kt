package com.happymeerkat.avocado.domain.use_case

import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryGetAll(
    private val repo: CategoryRepository
) {
    operator fun invoke(): Flow<List<Category>> {
        return repo.getAllCategories()
    }
}

