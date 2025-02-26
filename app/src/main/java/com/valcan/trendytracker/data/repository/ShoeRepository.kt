package com.valcan.trendytracker.data.repository

import com.valcan.trendytracker.data.dao.ShoeDao
import com.valcan.trendytracker.data.entities.ShoeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoeRepository @Inject constructor(
    private val shoeDao: ShoeDao
) : BaseRepository<ShoeEntity> {

    fun getShoesForUser(userId: Long): Flow<List<ShoeEntity>> = 
        shoeDao.getShoesForUser(userId)

    fun getShoesInWardrobe(wardrobeId: Long): Flow<List<ShoeEntity>> = 
        shoeDao.getShoesInWardrobe(wardrobeId)

    fun getShoesCountForUser(userId: Long): Flow<Int> = 
        shoeDao.getShoesCountForUser(userId)

    suspend fun getShoeById(shoeId: Long): ShoeEntity? = 
        shoeDao.getShoeById(shoeId)

    override suspend fun insert(item: ShoeEntity): Long = 
        shoeDao.insertShoe(item)

    override suspend fun update(item: ShoeEntity) = 
        shoeDao.updateShoe(item)

    override suspend fun delete(item: ShoeEntity) = 
        shoeDao.deleteShoe(item)
} 