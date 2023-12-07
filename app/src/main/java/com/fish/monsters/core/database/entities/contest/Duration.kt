package com.fish.monsters.core.database.entities.contest

data class Duration(val hours: Int = 0, val minutes: Int, val seconds: Int) {
    init {
        require(hours >= 0)
        require(minutes in 0..59)
        require(seconds in 0..59)
    }

    fun toString(includeSeconds: Boolean): String {
        val stringBuilder = StringBuilder()

        if (hours > 0) {
            stringBuilder.append("$hours h ")
        }

        stringBuilder.append("$minutes min")

        if (includeSeconds && seconds > 0) {
            stringBuilder.append(" $seconds s")
        }

        return stringBuilder.toString()
    }
}
