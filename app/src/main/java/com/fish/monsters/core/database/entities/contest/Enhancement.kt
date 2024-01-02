package com.fish.monsters.core.database.entities.contest

import com.fish.monsters.R

data class Enhancement(val name: String, val time: Duration) {
    val iconId get() = when (name) {
        "good_winds" -> R.drawable.good_winds_icon
        "quiet_zone" -> R.drawable.quiet_zone_icon
        "kraken_urine" -> R.drawable.kraken_urine_icon
        else -> throw IllegalArgumentException("Unknown enhancement name: $name")
    }
    fun getName(): Int {
        return when (name) {
            "good_winds" -> R.string.good_winds
            "quiet_zone" -> R.string.quiet_zone
            "kraken_urine" -> R.string.kraken_urine
            else -> throw IllegalArgumentException("Unknown enhancement name: $name")
        }
    }
}