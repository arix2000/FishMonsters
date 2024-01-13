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
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fish.monsters.R
import com.fish.monsters.common.models.ui.IconProps
import com.fish.monsters.common.views.buttons.IconFishButton
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.core.navigation.Screen
import com.fish.monsters.core.theme.LvlEasyColor
import com.fish.monsters.core.theme.LvlHardColor
import com.fish.monsters.core.theme.LvlMediumColor
import com.fish.monsters.features.game.models.Difficulty
import org.koin.compose.koinInject

@Composable
fun StartScreen(navigator: Navigator = koinInject()) {
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
                            navigator.navigateTo(
                                Screen.MainGameScreen,
                                Difficulty.EASY.name
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "EASY")
                }

                IconFishButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp),
                    iconProps = IconProps(icon = Icons.Default.QuestionMark),
                    onClick = {

                    })
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth().height(256.dp)
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
                            navigator.navigateTo(
                                Screen.MainGameScreen,
                                Difficulty.NORMAL.name
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "NORMAL")
                }

                IconFishButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp),
                    iconProps = IconProps(icon = Icons.Default.QuestionMark),
                    onClick = {

                    })
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
                            navigator.navigateTo(
                                Screen.MainGameScreen,
                                Difficulty.HARD.name
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "HARD")
                }
                IconFishButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp),
                    iconProps = IconProps(icon = Icons.Default.QuestionMark),
                    onClick = {

                    })
            }
        }
    }
}

