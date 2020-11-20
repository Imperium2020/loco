package com.imperium.loco.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pantry")
data class Pantry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val name: String,

    var price: Double
)