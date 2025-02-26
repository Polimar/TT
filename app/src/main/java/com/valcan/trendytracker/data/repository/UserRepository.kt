package com.valcan.trendytracker.data.repository

import com.valcan.trendytracker.data.dao.UserDao
import com.valcan.trendytracker.data.entities.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) : BaseRepository<UserEntity> {
    
    fun getAllUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()
    
    suspend fun getUserById(userId: Long): UserEntity? = userDao.getUserById(userId)
    
    override suspend fun insert(item: UserEntity): Long = userDao.insertUser(item)
    
    override suspend fun update(item: UserEntity) = userDao.updateUser(item)
    
    override suspend fun delete(item: UserEntity) = userDao.deleteUser(item)
} 