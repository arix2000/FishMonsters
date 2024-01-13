package com.fish.monsters.features.game.utils

import com.fish.monsters.core.database.entities.contest.AwardType
import com.fish.monsters.features.game.models.Difficulty
import kotlin.random.Random

class RandomFieldManager(difficulty: Difficulty) {

    fun getRandomAward(): AwardType? {
        val awards = mutableListOf<AwardType?>()
        for (type in AwardType.entries) {
            when (type) {
                AwardType.Grass -> awards.addAll(List(100) { AwardType.Grass })
                AwardType.Flower -> awards.addAll(List(50) { AwardType.Flower })
                AwardType.Pumpkin -> awards.addAll(List(10) { AwardType.Pumpkin })
            }
        }

        while (awards.size < 1000) {
            awards.add(null)
        }

        val randomIndex = Random.nextInt(awards.size)
        return awards.shuffled()[randomIndex]
    }
}