package com.imperium.loco.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "passengers",
    foreignKeys = [ForeignKey(
        entity = User::class, onDelete = ForeignKey.CASCADE,
        childColumns = ["booked_by"], parentColumns = ["id"]
    )],
)
data class Passenger(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "booked_by")
    val bookedBy: Long,

    val name: String,

    val age: Int,

    val gender: String,

    val aadhar: String
)