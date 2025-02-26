package com.valcan.trendytracker.data.dao

import androidx.room.*
import com.valcan.trendytracker.data.entities.ClothingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClothingDao {
    @Query("SELECT * FROM clothes WHERE userId = :userId")
    fun getClothesForUser(userId: Long): Flow<List<ClothingEntity>>

    @Query("SELECT * FROM clothes WHERE wardrobeId = :wardrobeId")
    fun getClothesInWardrobe(wardrobeId: Long): Flow<List<ClothingEntity>>

    @Query("SELECT COUNT(*) FROM clothes WHERE userId = :userId")
    fun getClothesCountForUser(userId: Long): Flow<Int>

    @Insert
    suspend fun insertClothing(clothing: ClothingEntity): Long

    @Update
    suspend fun updateClothing(clothing: ClothingEntity)

    @Delete
    suspend fun deleteClothing(clothing: ClothingEntity)

    @Query("SELECT * FROM clothes WHERE id = :clothingId")
    suspend fun getClothingById(clothingId: Long): ClothingEntity?
} 