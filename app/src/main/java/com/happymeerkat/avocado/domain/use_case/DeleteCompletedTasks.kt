package com.happymeerkat.avocado.domain.use_case

import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.repository.ListRepository

class DeleteCompletedTasks(
    private val repository: ListRepository
) {
    suspend operator fun invoke(category: Category) {
        if(category.name == "All") {
            repository.deleteAllCompleted()
        }else{
            repository.deleteAllCompletedByCategory(category)
        }
    }
}