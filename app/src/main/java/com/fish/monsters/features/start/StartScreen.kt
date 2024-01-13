package com.fish.monsters.features.start

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.fish.monsters.R
import com.fish.monsters.common.views.CapText
import com.fish.monsters.common.views.buttons.FishButton
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.core.navigation.Screen
import com.fish.monsters.features.game.models.Difficulty
import com.fish.monsters.features.preferences.PreferencesViewModel
import org.koin.compose.koinInject

@Composable
fun StartScreen(
    navigator: Navigator = koinInject(),
    viewModel: PreferencesViewModel = koinInject()
) {
    val shouldShowSafetyScreen =
        viewModel.shouldShowSafetyScreen.collectAsState(initial = true).value

    fun Navigator.navigateToAppropriateScreen(shouldShowSafetyScreen: Boolean, difficulty: String) {
        if (shouldShowSafetyScreen) {
            this.navigateTo(Screen.SafetyInformationScreen, difficulty)
        } else {
            this.navigateTo(Screen.MainGameScreen, difficulty)
        }
    }

    ScreenBox(title = stringResource(id = R.string.start)) {
        Column {
            FishButton(onClick = {
                navigator.navigateToAppropriateScreen(shouldShowSafetyScreen, Difficulty.EASY.name)
            }) {
                CapText(text = "easy")
            }
            FishButton(onClick = {
                navigator.navigateToAppropriateScreen(
                    shouldShowSafetyScreen,
                    Difficulty.NORMAL.name
                )
            }) {
                CapText(text = "Normal")
            }
            FishButton(onClick = {
                navigator.navigateToAppropriateScreen(shouldShowSafetyScreen, Difficulty.HARD.name)
            }) {
                CapText(text = "hard")
            }
        }
    }
}