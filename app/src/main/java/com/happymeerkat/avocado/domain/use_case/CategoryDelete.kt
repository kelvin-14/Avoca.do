package com.happymeerkat.avocado.domain.use_case

import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.repository.CategoryRepository
import com.happymeerkat.avocado.domain.repository.ListRepository

class CategoryDelete(
    private val catRepo: CategoryRepository,
    private val listRepo: ListRepository
) {
    suspend operator fun invoke(category: Category) {
        if(category.name != "All") {
            catRepo.deleteCategory(category)
            listRepo.deleteAllByCategory(category)
        }
    }
}