package com.fish.monsters.core.database.entities.contest

import com.fish.monsters.R

enum class AwardType(val awardName: Int, val points: Int) {
    Grass(R.string.grass_award, 10),
    Flower(R.string.flower_award, 25),
    Pumpkin(R.string.pumpkin_award, 50)
}

fun AwardType.getIcon(): Int {
    return when (this) {
        AwardType.Grass -> R.drawable.grass_icon
        AwardType.Flower -> R.drawable.flower_icon
        AwardType.Pumpkin -> R.drawable.pumpkin_icon
    }
}