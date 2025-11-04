package com.github.kououdev.crudroom.dao

import androidx.room.*
import com.github.kououdev.crudroom.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<User>
}