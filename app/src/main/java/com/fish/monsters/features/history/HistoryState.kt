package com.fish.monsters.features.history

import com.fish.monsters.core.database.entities.Contest

data class HistoryState(
    val contests: List<Contest> = emptyList(),
    val selectedContest: Contest? = null
)
