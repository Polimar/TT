package com.valcan.trendytracker.data.repository

import com.valcan.trendytracker.data.dao.ClothingDao
import com.valcan.trendytracker.data.entities.ClothingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClothingRepository @Inject constructor(
    private val clothingDao: ClothingDao
) : BaseRepository<ClothingEntity> {

    fun getClothesForUser(userId: Long): Flow<List<ClothingEntity>> = 
        clothingDao.getClothesForUser(userId)

    fun getClothesInWardrobe(wardrobeId: Long): Flow<List<ClothingEntity>> = 
        clothingDao.getClothesInWardrobe(wardrobeId)

    fun getClothesCountForUser(userId: Long): Flow<Int> = 
        clothingDao.getClothesCountForUser(userId)

    suspend fun getClothingById(clothingId: Long): ClothingEntity? = 
        clothingDao.getClothingById(clothingId)

    override suspend fun insert(item: ClothingEntity): Long = 
        clothingDao.insertClothing(item)

    override suspend fun update(item: ClothingEntity) = 
        clothingDao.updateClothing(item)

    override suspend fun delete(item: ClothingEntity) = 
        clothingDao.deleteClothing(item)
} 