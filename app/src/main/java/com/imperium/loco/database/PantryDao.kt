package com.imperium.loco.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PantryDao {
    @Insert
    fun createItem(foodItem: Pantry)

    @Update
    fun update(foodItem: Pantry)

    @Query("SELECT * from pantry WHERE id=(:key)")
    fun get(key: Long): Pantry?

    @Query("SELECT * from pantry WHERE name=(:name)")
    fun get(name: String): Pantry?

    @Query("SELECT price from pantry WHERE name=(:name)")
    fun getPrice(name: String): Double?
}