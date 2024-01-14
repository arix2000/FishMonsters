package com.fish.monsters.features.game.utils

import com.fish.monsters.core.database.entities.contest.AwardType
import com.fish.monsters.features.game.models.Difficulty
import com.fish.monsters.features.game.models.Enemy
import kotlin.random.Random

class RandomFieldManager(difficulty: Difficulty) {

    fun getRandomAward(): AwardType? {
        val awards = mutableListOf<AwardType?>()
        for (type in AwardType.entries) {
            when (type) {
                AwardType.Grass -> awards.addAll(List(50) { AwardType.Grass })
                AwardType.Flower -> awards.addAll(List(50) { AwardType.Flower })
                AwardType.Pumpkin -> awards.addAll(List(50) { AwardType.Pumpkin })
            }
        }

        while (awards.size < 1000) {
            awards.add(null)
        }

        val randomIndex = Random.nextInt(awards.size)
        return awards.shuffled()[randomIndex]
    }

    fun getRandomEnemy(): Enemy? {
        val enemies = mutableListOf<Enemy?>()
        for (type in Enemy.entries) {
            when (type) {
                Enemy.JELLYFISH -> enemies.addAll(List(50) { Enemy.JELLYFISH })
                Enemy.SHARK -> enemies.addAll(List(50) { Enemy.SHARK })
                Enemy.KRAKEN -> enemies.addAll(List(50) { Enemy.KRAKEN })
                Enemy.BOSS -> enemies.addAll(List(50) { Enemy.BOSS })
            }
        }

        while (enemies.size < 1000) {
            enemies.add(null)
        }

        val randomIndex = Random.nextInt(enemies.size)
        return enemies.shuffled()[randomIndex]
    }
}