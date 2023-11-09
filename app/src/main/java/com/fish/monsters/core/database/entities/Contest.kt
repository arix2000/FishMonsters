package com.fish.monsters.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fish.monsters.features.contest.DifficultyLevel
import com.fish.monsters.features.contest.Duration

@Entity(tableName = "contest_info")
data class ContestInfoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String,
    val duration: Duration,
    val points: Int,
    val difficultyLevel: DifficultyLevel
)