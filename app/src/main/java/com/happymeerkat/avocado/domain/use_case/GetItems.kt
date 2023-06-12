package com.happymeerkat.avocado.domain.use_case

import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow

class GetItems(
    private val repo: ListRepository
) {
    operator fun invoke(): Flow<List<ListItem>> {
        return repo.getAllListItems()
    }
}