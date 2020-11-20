package com.imperium.loco.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TicketDao {

    @Insert
    fun createTicket(newTicket: Ticket)

    @Update
    fun update(ticket: Ticket)

    @Query("SELECT * from tickets WHERE id=(:key)")
    fun get(key: Long): Ticket?

    @Query("SELECT passengers from tickets where id=(:key)")
    fun getPassengers(key: Long): List<String>?
}