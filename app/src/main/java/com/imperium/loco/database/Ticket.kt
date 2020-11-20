package com.imperium.loco.database

import androidx.room.*
import java.util.*


@Entity(
    tableName = "tickets", foreignKeys = [
        ForeignKey(
            entity = User::class, onDelete = ForeignKey.CASCADE,
            childColumns = ["user_id"], parentColumns = ["id"]
        ),
        ForeignKey(
            entity = Train::class, onDelete = ForeignKey.CASCADE,
            childColumns = ["train_id"], parentColumns = ["id"]
        ),
        ForeignKey(
            entity = Pantry::class, onDelete = ForeignKey.SET_NULL,
            childColumns = ["pantry_id"], parentColumns = ["id"]
        )]
)
data class Ticket(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "user_id")
    var userId: Long,

    @Embedded
    var seats: Seats,

    var passengers: List<String>?,

    var date: Date,

    @ColumnInfo(name = "train_id")
    var trainId: Long,

    @ColumnInfo(name = "pantry_id")
    var pantryId: List<PantryItem>? = null
)

