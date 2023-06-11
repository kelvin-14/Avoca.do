package com.happymeerkat.avocado.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.domain.model.ListItem

@Database(
    entities = [
        ListItem::class,
        Category::class
    ],
    version = 2,
    exportSchema = false
)
abstract class ListDatabase: RoomDatabase() {
    abstract fun getListDao(): ListDao
    abstract fun getCategoryDao(): CategoryDao

    companion object {
        val DATABASE_NAME = "List_Database"
    }
}