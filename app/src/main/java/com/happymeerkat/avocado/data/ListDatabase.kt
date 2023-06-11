package com.happymeerkat.avocado.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.happymeerkat.avocado.domain.model.ListItem

@Database(
    entities = [
        ListItem::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ListDatabase: RoomDatabase() {
    abstract fun getQuoteDao(): ListDao

    companion object {
        val DATABASE_NAME = "List_Database"
    }
}