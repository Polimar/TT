package com.valcan.trendytracker.data.repository

import com.valcan.trendytracker.data.dao.WardrobeDao
import com.valcan.trendytracker.data.entities.WardrobeEntity
import com.valcan.trendytracker.data.dao.WardrobeItemCounts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WardrobeRepository @Inject constructor(
    private val wardrobeDao: WardrobeDao
) : BaseRepository<WardrobeEntity> {

    fun getAllWardrobes(): Flow<List<WardrobeEntity>> = 
        wardrobeDao.getAllWardrobes()

    fun getWardrobesWithUserItems(userId: Long): Flow<List<WardrobeEntity>> = 
        wardrobeDao.getWardrobesWithUserItems(userId)

    suspend fun getWardrobeById(wardrobeId: Long): WardrobeEntity? = 
        wardrobeDao.getWardrobeById(wardrobeId)

    fun getItemCountsInWardrobe(wardrobeId: Long): Flow<WardrobeItemCounts> = 
        wardrobeDao.getItemCountsInWardrobe(wardrobeId)

    fun getUserItemCountsInWardrobe(wardrobeId: Long, userId: Long): Flow<WardrobeItemCounts> = 
        wardrobeDao.getUserItemCountsInWardrobe(wardrobeId, userId)

    override suspend fun insert(item: WardrobeEntity): Long = 
        wardrobeDao.insertWardrobe(item)

    override suspend fun update(item: WardrobeEntity) = 
        wardrobeDao.updateWardrobe(item)

    override suspend fun delete(item: WardrobeEntity) = 
        wardrobeDao.deleteWardrobe(item)
} 