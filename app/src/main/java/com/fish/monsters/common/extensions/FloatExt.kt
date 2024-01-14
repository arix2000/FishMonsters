package com.fish.monsters.common.extensions

import kotlin.math.roundToInt

fun Float.toPercents(): Int {
    return this.times(100).roundToInt()
}

fun Float.Companion.fromPercents(percents: Int): Float {
    return percents.toFloat().div(100)
}

fun Double.roundToString(decimals: Int): String {
    return String.format("%.${decimals}f", this)
}

fun Int.toReadableTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val remainingSeconds = this % 60
    return when {
        hours != 0 -> "${hours}h ${minutes}min ${remainingSeconds}s"
        minutes != 0 -> "${minutes}min ${remainingSeconds}s"
        else -> "${remainingSeconds}s"
    }
}
