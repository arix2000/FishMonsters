package com.fish.monsters.core.database.entities.contest

import com.fish.monsters.R

data class Award(
    val award: AwardType,
    val count: Int
) {
    fun getIconForAward(award: AwardType): Int {
        return when (award) {
            AwardType.Grass -> R.drawable.grass_icon
            AwardType.Flower -> R.drawable.flower_icon
            AwardType.Pumpkin -> R.drawable.pumpkin_icon
        }
    }
}
