package com.fish.monsters.features.game.presentation.ui.managers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.fish.monsters.features.game.models.Difficulty
import com.fish.monsters.features.game.models.Enemy
import com.fish.monsters.features.game.presentation.MainGameState
import com.fish.monsters.features.game.presentation.ui.map.MapEnemyField
import com.fish.monsters.features.game.utils.RandomFieldManager

@Composable
fun MapEnemyFieldsUiManager(state: MainGameState, difficulty: Difficulty) {
    val enemies: SnapshotStateList<Enemy> = remember { mutableStateListOf() }

    DisposableEffect(key1 = state.timeSeconds) {
        val enemyOrNull = RandomFieldManager(difficulty).getRandomEnemy()
        if (enemyOrNull != null)
            enemies.add(enemyOrNull)
        onDispose { }
    }

    enemies.forEachIndexed { index, enemy ->
        if (state.userLocation != null)
            MapEnemyField(state.userLocation, enemy) {
                enemies.removeAt(index)
            }
    }
}