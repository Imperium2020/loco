package com.imperium.loco.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    fun createUser(newUser: User)

    @Update
    fun update(user: User)

    @Query("SELECT * from users WHERE id=(:key)")
    fun get(key: Long): User?

    @Query("SELECT password from users WHERE user_name=(:name)")
    fun getPassword(name: String): String?

    @Query("SELECT * from users WHERE user_name=(:name)")
    fun login(name: String): User?

}