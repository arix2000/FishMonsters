package com.fish.monsters.features.game.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.fish.monsters.R
import com.fish.monsters.core.theme.BossColor
import com.fish.monsters.core.theme.BossColorDarkA80
import com.fish.monsters.core.theme.DangerColor
import com.fish.monsters.core.theme.DangerColorDarkA80
import com.fish.monsters.core.theme.EasyColor
import com.fish.monsters.core.theme.EasyColorDarkA80
import com.fish.monsters.core.theme.WarningColor
import com.fish.monsters.core.theme.WarningColorDarkA80

enum class Enemy(
    val color: Color,
    val colorDark: Color,
    @DrawableRes val icon: Int,
    val radius: Double
) {
    JELLYFISH(EasyColor, EasyColorDarkA80, R.drawable.jellyfish_icon, 3.0),
    SHARK(WarningColor, WarningColorDarkA80, R.drawable.shark_icon, 4.0),
    KRAKEN(DangerColor, DangerColorDarkA80, R.drawable.kraken_icon, 6.0),
    BOSS(BossColor, BossColorDarkA80, R.drawable.fish_monter_icon, 8.0)
}
