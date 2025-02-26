package com.valcan.trendytracker.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "shoes",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = WardrobeEntity::class,
            parentColumns = ["id"],
            childColumns = ["wardrobeId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index("userId"),
        Index("wardrobeId")
    ]
)
data class ShoeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val wardrobeId: Long?,  // Può essere null se non è in nessun armadio
    val name: String,
    val size: String,
    val color: String,
    val type: String,
    val imageUrl: String?
) 