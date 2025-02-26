package com.valcan.trendytracker.data.dao

import androidx.room.*
import com.valcan.trendytracker.data.entities.WardrobeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WardrobeDao {
    @Query("SELECT * FROM wardrobes")
    fun getAllWardrobes(): Flow<List<WardrobeEntity>>

    @Query("""
        SELECT DISTINCT w.* 
        FROM wardrobes w 
        LEFT JOIN clothes c ON w.id = c.wardrobeId 
        LEFT JOIN shoes s ON w.id = s.wardrobeId 
        WHERE c.userId = :userId OR s.userId = :userId
    """)
    fun getWardrobesWithUserItems(userId: Long): Flow<List<WardrobeEntity>>

    @Insert
    suspend fun insertWardrobe(wardrobe: WardrobeEntity): Long

    @Update
    suspend fun updateWardrobe(wardrobe: WardrobeEntity)

    @Delete
    suspend fun deleteWardrobe(wardrobe: WardrobeEntity)

    @Query("SELECT * FROM wardrobes WHERE id = :wardrobeId")
    suspend fun getWardrobeById(wardrobeId: Long): WardrobeEntity?

    @Query("""
        SELECT 
            (SELECT COUNT(*) FROM clothes WHERE wardrobeId = :wardrobeId) as clothesCount,
            (SELECT COUNT(*) FROM shoes WHERE wardrobeId = :wardrobeId) as shoesCount
    """)
    fun getItemCountsInWardrobe(wardrobeId: Long): Flow<WardrobeItemCounts>

    @Query("""
        SELECT 
            (SELECT COUNT(*) FROM clothes WHERE wardrobeId = :wardrobeId AND userId = :userId) as clothesCount,
            (SELECT COUNT(*) FROM shoes WHERE wardrobeId = :wardrobeId AND userId = :userId) as shoesCount
    """)
    fun getUserItemCountsInWardrobe(wardrobeId: Long, userId: Long): Flow<WardrobeItemCounts>
}

data class WardrobeItemCounts(
    val clothesCount: Int,
    val shoesCount: Int
) 