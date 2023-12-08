package com.fish.monsters.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.fish.monsters.core.database.converters.ContestConverter
import com.fish.monsters.core.database.entities.contest.Award
import com.fish.monsters.core.database.entities.contest.DifficultyLevel
import com.fish.monsters.core.database.entities.contest.Duration
import com.fish.monsters.core.database.entities.contest.Enhancement
import com.fish.monsters.core.database.entities.contest.GameLocation

@TypeConverters(ContestConverter::class)
@Entity(tableName = "contest_info")
data class Contest(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val duration: Duration,
    val date: String,
    val points: Int,
    val difficultyLevel: DifficultyLevel,
    val rewardsCount: Int,
    val enhancementsUsed: List<Enhancement>,
    val bypassedMonsters: Int,
    val awardsEarned: List<Award>,
    val isGameSuccess: Boolean,
    val gameLocation: GameLocation
)


