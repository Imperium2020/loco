package com.imperium.loco.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "UserTable")
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,
    @ColumnInfo(name = "fullName")
    var fullName: String = "full name",
    @ColumnInfo(name = "userName")
    var userName: String = "username",
    @ColumnInfo(name = "email")
    var email: String = "email",
    @ColumnInfo(name = "phone")
    var phone: String = "9876543210",
    @ColumnInfo(name = "password")
    var password: String = "pass"
)