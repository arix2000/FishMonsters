package com.fish.monsters.features.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fish.monsters.R
import com.fish.monsters.common.models.ui.Language
import com.fish.monsters.common.views.CapText
import com.fish.monsters.common.views.FadingHorizontalDivider
import com.fish.monsters.common.views.FishSwitch
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.common.views.buttons.DangerOutlinedFishButton
import com.fish.monsters.common.views.buttons.DropdownTextButton
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.features.settings.data.SettingsManager
import com.fish.monsters.features.settings.presentation.components.SettingsButtonGridSection
import com.fish.monsters.features.settings.presentation.components.SettingsRowSection
import com.fish.monsters.features.settings.presentation.components.SettingsSliderSection
import org.koin.compose.koinInject


@Composable
fun SettingsScreen(viewModel: SettingsViewModel = koinInject()) {
    SettingsScreenContent(viewModel)
}

@Composable
private fun SettingsScreenContent(viewModel: SettingsViewModel) {
    val state = viewModel.state.value
    ScreenBox(title = stringResource(R.string.settings)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 30.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SettingsRowSection(label = stringResource(R.string.language)) {
                val context = LocalContext.current
                DropdownTextButton(
                    items = Language.values().toList(),
                    onItemClicked = {
                        viewModel.changeLanguage(context, it)
                    }, defaultButtonText = stringResource(state.language.titleId)
                )
            }
            SettingsRowSection(label = stringResource(R.string.vibration)) {
                FishSwitch(checked = state.vibration, onCheckedChange = {
                    viewModel.updateSettings(vibration = it)
                })
            }
            SettingsSliderSection(
                label = stringResource(R.string.music),
                value = state.musicPercentage,
                onValueChange = { viewModel.updateSettings(musicPercentage = it) })
            SettingsSliderSection(
                label = stringResource(R.string.sound),
                value = state.soundPercentage,
                onValueChange = { viewModel.updateSettings(soundPercentage = it) })
            FadingHorizontalDivider()
            SettingsRowSection(label = stringResource(R.string.neon_styles)) {
                FishSwitch(checked = state.neonStyles, onCheckedChange = {
                    viewModel.updateSettings(neonStyles = it)
                })
            }
            SettingsButtonGridSection()
            DangerOutlinedFishButton(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                CapText(text = stringResource(R.string.clear_progress))
            }
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    PreviewContainer {
        SettingsScreenContent(SettingsViewModel(SettingsManager()))
    }
}