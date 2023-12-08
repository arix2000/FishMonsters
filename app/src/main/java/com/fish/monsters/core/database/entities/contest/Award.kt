package com.fish.monsters.core.database.entities.contest

import com.fish.monsters.R

enum class Award(val awardName: Int, val points: Int) {
    Grass(R.string.grass_award, 10),
    Flower(R.string.flower_award, 25),
    Pumpkin(R.string.pumpkin_award, 50)
}