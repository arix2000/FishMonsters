package com.fish.monsters.core.database.entities.contest

import com.fish.monsters.R

data class AwardsCount(
    val award: Award,
    val count: Int
) {
    fun getIconForAward(award: Award): Int {
        return when (award) {
            Award.Grass -> R.drawable.grass_icon
            Award.Flower -> R.drawable.flower_icon
            Award.Pumpkin -> R.drawable.pumpkin_icon
        }
    }
}
