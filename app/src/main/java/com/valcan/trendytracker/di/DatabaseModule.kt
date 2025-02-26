package com.valcan.trendytracker.di

import android.content.Context
import com.valcan.trendytracker.data.*
import com.valcan.trendytracker.data.dao.*
import com.valcan.trendytracker.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    fun provideClothingDao(database: AppDatabase): ClothingDao = database.clothingDao()

    @Provides
    fun provideShoeDao(database: AppDatabase): ShoeDao = database.shoeDao()

    @Provides
    fun provideWardrobeDao(database: AppDatabase): WardrobeDao = database.wardrobeDao()

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository = 
        UserRepository(userDao)

    @Provides
    @Singleton
    fun provideClothingRepository(clothingDao: ClothingDao): ClothingRepository = 
        ClothingRepository(clothingDao)

    @Provides
    @Singleton
    fun provideShoeRepository(shoeDao: ShoeDao): ShoeRepository = 
        ShoeRepository(shoeDao)

    @Provides
    @Singleton
    fun provideWardrobeRepository(wardrobeDao: WardrobeDao): WardrobeRepository = 
        WardrobeRepository(wardrobeDao)
} 