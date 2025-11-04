package com.github.kououdev.crudroom.dao

import androidx.room.*
import com.github.kououdev.crudroom.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}