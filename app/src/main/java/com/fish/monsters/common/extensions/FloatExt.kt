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
