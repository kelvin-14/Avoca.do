package com.happymeerkat.avocado.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import androidx.room.Query
import com.happymeerkat.avocado.domain.model.ListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao {
    @Query("SELECT * FROM list WHERE completed = 0")
    fun getAllListItems(): Flow<List<ListItem>>

    @Upsert
    suspend fun upsertListItem(item: ListItem)

    @Delete
    suspend fun deleteListItem(item: ListItem)
}