package com.imperium.loco.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trains")
data class Train(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "source")
    var src: String,

    @ColumnInfo(name = "destination")
    var dest: String,

    var arrival: String,

    var departure: String,

    @Embedded
    var seats: Seats,
)