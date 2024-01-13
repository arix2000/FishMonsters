package com.fish.monsters.features.safetyInformation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fish.monsters.R
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.common.views.SafetyInformationCard
import com.fish.monsters.common.views.buttons.FishButton
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.database.dataStore.dataStore
import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.core.navigation.Screen
import com.fish.monsters.core.theme.TextColorLight
import com.fish.monsters.features.game.models.Difficulty
import com.fish.monsters.features.preferences.PreferencesViewModel
import org.koin.compose.koinInject

@Composable
fun SafetyInformationScreen(
    navigator: Navigator = koinInject(),
    preferencesViewModel: PreferencesViewModel = koinInject(),
    difficulty: Difficulty
) {
    var isChecked by remember { mutableStateOf(false) }

    val shouldShowSafetyScreen =
        preferencesViewModel.shouldShowSafetyScreen.collectAsState(initial = true).value

    if (!shouldShowSafetyScreen) {
        navigator.navigateTo(Screen.MainGameScreen, difficulty.name)
    }

    ScreenBox(title = stringResource(R.string.safety_information)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 30.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.safety_warning),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = TextColorLight
            )

            SafetyInformationCard(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.safety_info_text),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(all = 20.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                    }
                )

                Spacer(Modifier.width(8.dp))
                Text(text = stringResource(id = R.string.do_not_show_again))
            }

            Spacer(modifier = Modifier.weight(1f))

            FishButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                onClick = {
                    preferencesViewModel.setShowSafetyScreen(!isChecked)
                    navigator.navigateTo(Screen.MainGameScreen, difficulty.name)
                }

            ) {
                Text(
                    text = stringResource(id = R.string.start),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview
@Composable
private fun SafetyInformationScreenPreview() {
    val context = LocalContext.current
    PreviewContainer {
        SafetyInformationScreen(
            navigator = Navigator(),
            preferencesViewModel = PreferencesViewModel(context.dataStore),
            difficulty = Difficulty.EASY
        )
    }
}


