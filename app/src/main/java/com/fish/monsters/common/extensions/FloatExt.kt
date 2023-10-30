package com.fish.monsters.common.extensions

import kotlin.math.roundToInt

fun Float.toPercents(): Int {
    return this.times(100).roundToInt()
}

fun Int.fromPercents(): Float {
    return this.toFloat().div(100)
}