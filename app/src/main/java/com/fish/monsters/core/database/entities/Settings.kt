package com.fish.monsters.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Settings(
    @PrimaryKey
    val id: Int = 0,
    val vibration: Boolean,
    val musicPercentage: Int,
    val soundPercentage: Int,
    val neonStyles: Boolean
) {
    companion object {
        fun default(): Settings = Settings(
            vibration = true,
            musicPercentage = 100,
            soundPercentage = 100,
            neonStyles = false,
        )
    }
}