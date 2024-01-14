package com.fish.monsters.features.game.presentation.ui.managers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.fish.monsters.core.database.entities.contest.AwardType
import com.fish.monsters.features.game.models.Difficulty
import com.fish.monsters.features.game.presentation.MainGameState
import com.fish.monsters.features.game.presentation.ui.map.MapAwardField
import com.fish.monsters.features.game.utils.RandomFieldManager
import com.google.maps.android.compose.GoogleMapComposable

@Composable
@GoogleMapComposable
fun MapAwardFieldsUiManager(state: MainGameState, difficulty: Difficulty) {
    val awards: SnapshotStateList<AwardType> = remember { mutableStateListOf() }

    DisposableEffect(key1 = state.timeSeconds) {
        val awardOrNull = RandomFieldManager(difficulty).getRandomAward()
        if (awardOrNull != null)
            awards.add(awardOrNull)
        onDispose { }
    }

    awards.forEachIndexed { index, award ->
        if (state.userLocation != null)
            MapAwardField(userLocation = state.userLocation, type = award, onAwardRemoveRequest = {
                awards.removeAt(index)
            })
    }
}