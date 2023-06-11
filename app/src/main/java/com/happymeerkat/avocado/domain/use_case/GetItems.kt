package com.happymeerkat.avocado.domain.use_case

import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetItems(
    private val repo: ListRepository
) {
    operator fun invoke(category: String): Flow<List<ListItem>> {
        return repo.getAllListItems().map { listOfItems ->
            if(category == "ALL"){
                listOfItems
            }else{
                listOfItems.filter {  it.category == category }
            }
        }
    }
}