package com.valcan.trendytracker.data.repository

interface BaseRepository<T> {
    suspend fun insert(item: T): Long
    suspend fun update(item: T)
    suspend fun delete(item: T)
} 