package com.fish.monsters.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fish.monsters.core.database.dao.ContestDao
import com.fish.monsters.core.database.dao.SettingsDao
import com.fish.monsters.core.database.entities.ContestInfoEntity
import com.fish.monsters.core.database.entities.Settings

@Database(entities = [Settings::class, ContestInfoEntity::class], version = 1, exportSchema = false)
@TypeConverters(DurationConverter::class)
abstract class FishDatabase : RoomDatabase() {

    abstract fun settingsDao(): SettingsDao
    abstract fun contestInfoDao(): ContestDao

    suspend fun deleteAllTables() {
        settingsDao().insert(Settings.default())
    }

    companion object {
        @Volatile
        private var Instance: FishDatabase? = null

        fun getDatabase(context: Context): FishDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FishDatabase::class.java, "fish_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}