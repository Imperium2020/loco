package com.imperium.loco.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PassengerDao {

    @Insert
    fun createUser(newPassenger: Passenger)

    @Update
    fun update(passenger: Passenger)

    @Query("SELECT * from passengers WHERE name=(:name) and booked_by=(:userId)")
    fun get(name: String, userId: Long): Passenger?

    @Query("SELECT * from passengers WHERE booked_by=(:userId)")
    fun getByUserId(userId: Long): Passenger?
}