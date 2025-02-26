package com.valcan.trendytracker.data.dao

import androidx.room.*
import com.valcan.trendytracker.data.entities.ShoeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoeDao {
    @Query("SELECT * FROM shoes WHERE userId = :userId")
    fun getShoesForUser(userId: Long): Flow<List<ShoeEntity>>

    @Query("SELECT * FROM shoes WHERE wardrobeId = :wardrobeId")
    fun getShoesInWardrobe(wardrobeId: Long): Flow<List<ShoeEntity>>

    @Query("SELECT COUNT(*) FROM shoes WHERE userId = :userId")
    fun getShoesCountForUser(userId: Long): Flow<Int>

    @Insert
    suspend fun insertShoe(shoe: ShoeEntity): Long

    @Update
    suspend fun updateShoe(shoe: ShoeEntity)

    @Delete
    suspend fun deleteShoe(shoe: ShoeEntity)

    @Query("SELECT * FROM shoes WHERE id = :shoeId")
    suspend fun getShoeById(shoeId: Long): ShoeEntity?
} 