package com.valcan.trendytracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.valcan.trendytracker.data.dao.UserDao
import com.valcan.trendytracker.data.dao.ClothingDao
import com.valcan.trendytracker.data.dao.ShoeDao
import com.valcan.trendytracker.data.dao.WardrobeDao
import com.valcan.trendytracker.data.entities.*

@Database(
    entities = [
        UserEntity::class,
        ClothingEntity::class,
        ShoeEntity::class,
        WardrobeEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun clothingDao(): ClothingDao
    abstract fun shoeDao(): ShoeDao
    abstract fun wardrobeDao(): WardrobeDao
    // Altri DAO da aggiungere...

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "trendy_tracker_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
} 