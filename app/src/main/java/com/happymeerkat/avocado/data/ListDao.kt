package com.happymeerkat.avocado.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import androidx.room.Query
import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.model.ListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao {
    @Query("SELECT * FROM list ORDER BY completed, dateMade DESC")
    fun getAllListItems(): Flow<List<ListItem>>

    @Query("SELECT * FROM list WHERE id = :id")
    suspend fun getItemById(id: Int): ListItem?

    @Upsert
    suspend fun upsertListItem(item: ListItem)

    @Delete
    suspend fun deleteListItem(item: ListItem)

    @Query("DELETE FROM list WHERE categoryId = :categoryId AND completed = 1")
    suspend fun deleteAllCompletedByCategory(categoryId: Int)

    @Query("DELETE FROM list WHERE categoryId = :categoryId")
    suspend fun deleteAllByCategory(categoryId: Int)

    @Query("DELETE FROM list WHERE completed = 1")
    suspend fun deleteAllCompleted()

    @Query("SELECT * FROM list WHERE completed = 0 AND timeDue > :currentTime")
    suspend fun getActiveAlarms(currentTime: Long): List<ListItem>
}