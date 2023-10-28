package com.fish.monsters.features.game

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fish.monsters.core.theme.FishMonstersTheme
import com.fish.monsters.features.game.models.Difficulty

@Composable
fun MainGameScreen(difficulty: Difficulty) {
    Text(text = difficulty.name)
}

@Preview
@Composable
fun MainGameScreenPreview() {
    FishMonstersTheme {
        Surface {
            MainGameScreen(Difficulty.HARD)
        }
    }
}