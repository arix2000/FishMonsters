package com.fish.monsters.features.contest

import androidx.compose.ui.graphics.Color
import com.fish.monsters.core.theme.DangerColor
import com.fish.monsters.core.theme.EasyColor
import com.fish.monsters.core.theme.WarningColor

data class Duration(val hours: Int, val minutes: Int) {
    init {
        require(hours >= 0)
        require(minutes in 0..59)
    }
}


enum class DifficultyLevel(val color: Color) {
    LOW(DangerColor),
    MEDIUM(WarningColor),
    HIGH(EasyColor)
}
