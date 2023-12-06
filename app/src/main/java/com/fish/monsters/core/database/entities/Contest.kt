package com.fish.monsters.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.fish.monsters.core.database.GsonConverter
import com.fish.monsters.core.database.entities.contest.Award
import com.fish.monsters.core.database.entities.contest.DifficultyLevel
import com.fish.monsters.core.database.entities.contest.Duration
import com.fish.monsters.core.database.entities.contest.Enhancement
import com.fish.monsters.core.database.entities.contest.GameLocation

@Entity(tableName = "contest_info")
data class Contest(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String,
    @TypeConverters(GsonConverter::class)
    val duration: Duration,
    val points: Int,
    val difficultyLevel: DifficultyLevel,
    val rewardsCount: Int,
    @TypeConverters(GsonConverter::class)
    val enhancementsUsed: List<Enhancement>,
    val bypassedMonsters: Int,
    @TypeConverters(GsonConverter::class)
    val awardsEarned: List<Award>,
    val isGameSuccess: Boolean,
    @TypeConverters(GsonConverter::class)
    val gameLocation: GameLocation
)


