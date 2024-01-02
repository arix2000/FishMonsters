package com.fish.monsters.core.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.fish.monsters.core.database.entities.contest.Award
import com.fish.monsters.core.database.entities.contest.Enhancement
import com.fish.monsters.core.database.entities.contest.GameLocation
import com.fish.monsters.core.database.entities.contest.DifficultyLevel
import com.fish.monsters.core.database.entities.contest.Duration

class ContestConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromDuration(value: Duration): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toDuration(value: String): Duration {
        return gson.fromJson(value, Duration::class.java)
    }

    @TypeConverter
    fun fromAwardsCountList(value: List<Award>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toAwardsCountList(value: String): List<Award> {
        val listType = object : TypeToken<List<Award>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromEnhancementList(value: List<Enhancement>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toEnhancementList(value: String): List<Enhancement> {
        val listType = object : TypeToken<List<Enhancement>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromGameLocation(value: GameLocation): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toGameLocation(value: String): GameLocation {
        return gson.fromJson(value, GameLocation::class.java)
    }

    @TypeConverter
    fun fromDifficultyLevel(value: DifficultyLevel): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toDifficultyLevel(value: String): DifficultyLevel {
        return gson.fromJson(value, DifficultyLevel::class.java)
    }
}
