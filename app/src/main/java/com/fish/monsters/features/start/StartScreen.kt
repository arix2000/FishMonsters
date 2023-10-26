package com.fish.monsters.features.start

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.fish.monsters.R
import com.fish.monsters.common.views.CapText
import com.fish.monsters.common.views.buttons.FishButton
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.core.navigation.Screen
import com.fish.monsters.features.game.models.Difficulty
import org.koin.compose.koinInject

@Composable
fun StartScreen(navigator: Navigator = koinInject()) {
    ScreenBox(title = stringResource(id = R.string.start)) {
        Column {
            FishButton(onClick = {
                navigator.navigateTo(
                    Screen.MainGameScreen,
                    Difficulty.EASY.name
                )
            }) {
                CapText(text = "easy")
            }
            FishButton(onClick = {
                navigator.navigateTo(
                    Screen.MainGameScreen,
                    Difficulty.NORMAL.name
                )
            }) {
                CapText(text = "Normal")
            }
            FishButton(onClick = {
                navigator.navigateTo(
                    Screen.MainGameScreen,
                    Difficulty.HARD.name
                )
            }) {
                CapText(text = "hard")
            }
        }
    }
}