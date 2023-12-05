// DurationConverter.kt
package com.fish.monsters.core.database

import androidx.room.TypeConverter
import com.fish.monsters.features.contest.Duration

class DurationConverter {

    @TypeConverter
    fun fromDuration(duration: Duration): String {
        return "${duration.hours}:${duration.minutes}"
    }

    @TypeConverter
    fun toDuration(durationString: String): Duration {
        val parts = durationString.split(":")
        return Duration(parts[0].toInt(), parts[1].toInt())
    }
}
