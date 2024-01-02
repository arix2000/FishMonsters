package com.fish.monsters.features.history

import com.fish.monsters.core.database.entities.Contest
import com.fish.monsters.core.database.entities.contest.DifficultyLevel
import com.fish.monsters.core.database.entities.contest.Duration
import com.fish.monsters.core.database.entities.contest.GameLocation

data class HistoryState(
    val contests: List<Contest> = emptyList(),
    val selectedContest: Contest = Contest(
        duration = Duration(0, 0, 0),
        date = "",
        points = 0,
        difficultyLevel = DifficultyLevel.MEDIUM,
        rewardsCount = 0,
        enhancementsUsed = emptyList(),
        bypassedMonsters = 0,
        awardsEarned = emptyList(),
        isGameSuccess = false,
        gameLocation = GameLocation(0.0, 0.0)
    )
)
