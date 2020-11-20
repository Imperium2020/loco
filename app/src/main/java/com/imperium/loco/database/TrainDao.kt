package com.imperium.loco.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TrainDao {

    @Insert
    fun scheduleTrain(newTrain: Train)

    @Update
    fun update(train: Train)

    @Query("SELECT * from trains WHERE id=(:key)")
    fun get(key: Long): Train?

    @Query("SELECT * from trains WHERE source=(:src) and destination=(:dest)")
    fun get(src: String, dest: String): Train?
}