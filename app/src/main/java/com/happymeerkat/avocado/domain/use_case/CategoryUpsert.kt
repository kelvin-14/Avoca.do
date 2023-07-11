package com.happymeerkat.avocado.domain.use_case

import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.repository.CategoryRepository

class CategoryUpsert(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(category: Category): Category {
        categoryRepository.upsertCategory(category)
        return category
    }
}