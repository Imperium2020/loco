package com.imperium.loco.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppDatabaseDao {

    @Insert
    fun createUser(newUser: User)

    @Update
    fun update(user: User)

    @Query("SELECT * from UserTable WHERE userId = :key")
    fun get(key: Long): User?

}