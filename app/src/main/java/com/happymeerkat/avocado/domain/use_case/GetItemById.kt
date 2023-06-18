package com.happymeerkat.avocado.domain.use_case

import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.domain.repository.ListRepository

class GetItemById(
    private val repo: ListRepository
) {
    suspend operator fun invoke(id: Int): ListItem? {
        return repo.getItemById(id)
    }
}