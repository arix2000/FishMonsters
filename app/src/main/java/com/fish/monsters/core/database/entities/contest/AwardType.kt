package com.fish.monsters.core.database.entities.contest

import com.fish.monsters.R

enum class AwardType(val awardName: Int, val points: Int, val icon: Int) {
    Grass(R.string.grass_award, 10,R.drawable.grass_icon),
    Flower(R.string.flower_award, 25,R.drawable.flower_icon),
    Pumpkin(R.string.pumpkin_award, 50,R.drawable.pumpkin_icon)
}