package com.happymeerkat.avocado.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "category"
)
data class Category(
    @PrimaryKey
    val id: Int,
    val name: String
)
