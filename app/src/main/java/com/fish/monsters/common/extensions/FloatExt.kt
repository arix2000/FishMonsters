package com.fish.monsters.common.extensions

import kotlin.math.roundToInt

fun Float.toPercentString(): String {
    return this.times(100).roundToInt().toString()
}