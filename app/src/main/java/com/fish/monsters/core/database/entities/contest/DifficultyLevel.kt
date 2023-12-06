package com.fish.monsters.core.database.entities.contest

import androidx.compose.ui.graphics.Color
import com.fish.monsters.core.theme.DangerColor
import com.fish.monsters.core.theme.EasyColor
import com.fish.monsters.core.theme.WarningColor

enum class DifficultyLevel(val color: Color) {
    LOW(DangerColor),
    MEDIUM(WarningColor),
    HIGH(EasyColor)
}