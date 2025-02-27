package com.happymeerkat.avocado.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "list",
)
data class ListItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val description: String? = null,
    val categoryId: Int? = null,
    val dateMade: Long? = null,
    val dateDue: Long? = null,
    val timeDue: Long? = null,
    var completed: Boolean = false
): Serializable
