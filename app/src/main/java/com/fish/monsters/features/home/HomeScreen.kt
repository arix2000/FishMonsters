package com.fish.monsters.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.R
import com.fish.monsters.common.views.CapText
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.common.views.buttons.FishButton
import com.fish.monsters.common.views.buttons.OutlinedFishButton
import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.core.navigation.Screen
import com.fish.monsters.core.theme.SurfaceColor
import org.koin.compose.koinInject

@Composable
fun HomeScreen(navigator: Navigator = koinInject()) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.kraken_home_screen),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .drawWithContent {
                    drawContent()
                    drawRect(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                SurfaceColor
                            )
                        )
                    )
                }
        )
        Spacer(modifier = Modifier.height(15.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 35.dp),
            verticalArrangement = Arrangement.spacedBy(35.dp)
        ) {
            FishButton(
                indentationSize = DpSize(12.dp, 31.dp),
                onClick = { navigator.navigateTo(Screen.StartScreen) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                CapText(text = stringResource(R.string.start))
            }
            HomeOutlineFishButton(
                stringResource(R.string.settings),
                onClick = { navigator.navigateTo(Screen.SettingsScreen) })
            HomeOutlineFishButton(
                stringResource(R.string.history),
                onClick = { navigator.navigateTo(Screen.HistoryScreen) })
            HomeOutlineFishButton(
                stringResource(R.string.about),
                onClick = { navigator.navigateTo(Screen.AboutScreen) })
        }
    }
}

@Composable
private fun HomeOutlineFishButton(buttonText: String, onClick: () -> Unit) {
    OutlinedFishButton(
        indentationSize = DpSize(12.dp, 31.dp),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        CapText(text = buttonText)
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    PreviewContainer(modifier = Modifier.fillMaxSize()) {
        HomeScreen(Navigator())
    }
}