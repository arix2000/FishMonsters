package com.fish.monsters.common.models.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.fish.monsters.core.theme.SurfaceColor

data class IconProps(
    val icon: ImageVector,
    val contentDescription: String = icon.name,
    val tint: Color = SurfaceColor,
    val iconModifier: Modifier = Modifier
)
