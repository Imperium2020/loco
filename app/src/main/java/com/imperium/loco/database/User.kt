package com.imperium.loco.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "full_name")
    val fullName: String,

    @ColumnInfo(name = "user_name")
    val userName: String,

    val email: String,

    val phone: String,

    var password: String,


    )