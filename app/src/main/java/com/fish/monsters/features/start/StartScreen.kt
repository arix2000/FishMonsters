package com.fish.monsters.features.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fish.monsters.R
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.core.navigation.Screen
import com.fish.monsters.core.theme.LvlEasyColor
import com.fish.monsters.core.theme.LvlHardColor
import com.fish.monsters.core.theme.LvlMediumColor
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
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Image(
                    painter = painterResource(R.drawable.level_easy),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(254.dp)
                )

                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(258.dp)
                        .background(LvlEasyColor)
                        .clickable {
                            navigator.navigateToAppropriateScreen(
                                shouldShowSafetyScreen,
                                Difficulty.EASY.name
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(id = R.string.low_difficulty).uppercase())
                }


            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
            ) {

                Image(
                    painter = painterResource(R.drawable.level_medium),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()

                )

                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .background(LvlMediumColor)
                        .clickable {
                            navigator.navigateToAppropriateScreen(
                                shouldShowSafetyScreen,
                                Difficulty.NORMAL.name
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(id = R.string.medium_difficulty).uppercase())
                }


            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Image(
                    painter = painterResource(R.drawable.level_hard),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                )

                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .background(LvlHardColor)
                        .clickable {
                            navigator.navigateToAppropriateScreen(
                                shouldShowSafetyScreen,
                                Difficulty.HARD.name
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(id = R.string.high_difficulty).uppercase())
                }

            }
        }
    }
}


@Preview
@Composable
private fun StartScreenPreview() {
    PreviewContainer {
        StartScreen(
            Navigator()
        )
    }
}