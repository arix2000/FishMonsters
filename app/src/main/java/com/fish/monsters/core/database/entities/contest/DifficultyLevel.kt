package com.fish.monsters.core.database.entities.contest

import androidx.compose.ui.graphics.Color
import com.fish.monsters.R
import com.fish.monsters.core.theme.DangerColor
import com.fish.monsters.core.theme.EasyColor
import com.fish.monsters.core.theme.WarningColor

enum class DifficultyLevel(val color: Color, val stringRes: Int) {
    LOW(EasyColor, R.string.low_difficulty),
    MEDIUM(WarningColor, R.string.medium_difficulty),
    HIGH(DangerColor, R.string.high_difficulty)
}
