package com.github.kououdev.crudroom.repository

import com.github.kououdev.crudroom.dao.UserDao
import com.github.kououdev.crudroom.entity.User

class UserRepository(private val userDao: UserDao) {
    suspend fun insert(user: User) = userDao.insert(user)
    suspend fun update(user: User) = userDao.update(user)
    suspend fun delete(user: User) = userDao.delete(user)
    suspend fun getAll() = userDao.getAll()
}