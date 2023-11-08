package com.fish.monsters.features.contest

import androidx.compose.ui.graphics.Color
import com.fish.monsters.core.theme.DangerColor
import com.fish.monsters.core.theme.EasyColor
import com.fish.monsters.core.theme.WarningColor

data class ContestInfo(
    val date: String,
    val duration: Duration,
    val score: Int,
    val difficulty: DifficultyLevel
)

data class Duration(val hours: Int, val minutes: Int)

enum class DifficultyLevel(val color: Color) {
    LOW(DangerColor),
    MEDIUM(WarningColor),
    HIGH(EasyColor)
}
