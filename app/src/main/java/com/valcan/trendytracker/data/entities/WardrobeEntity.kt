package com.valcan.trendytracker.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wardrobes")
data class WardrobeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val location: String,
    val description: String?
) 