package com.happymeerkat.avocado.domain.use_case

import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.repository.CategoryRepository

class CreateCategory(
    private val repo: CategoryRepository
) {
    suspend operator fun invoke(category: Category) {
        repo.upsertCategory(category)
    }
}