package com.fish.monsters.features.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fish.monsters.R
import com.fish.monsters.common.models.ui.Language
import com.fish.monsters.common.utils.settings.SettingsGlobalState
import com.fish.monsters.common.views.CapText
import com.fish.monsters.common.views.FadingHorizontalDivider
import com.fish.monsters.common.views.FishSwitch
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.common.views.buttons.DangerOutlinedFishButton
import com.fish.monsters.common.views.buttons.DropdownTextButton
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.SettingsUris
import com.fish.monsters.features.settings.SettingsViewModel
import com.fish.monsters.features.settings.ui.components.SettingsButtonGridSection
import com.fish.monsters.features.settings.ui.components.SettingsClearProgressDialog
import com.fish.monsters.features.settings.ui.components.SettingsRowSection
import com.fish.monsters.features.settings.ui.components.SettingsSliderSection
import org.koin.compose.koinInject


@Composable
fun SettingsScreen(viewModel: SettingsViewModel = koinInject()) {
    val isClearProgressDialogVisible = remember { mutableStateOf(false) }
    SettingsScreenContent(
        viewModel.settingsGlobalState.value, isClearProgressDialogVisible
    ) { viewModel.invokeEvent(it) }
}

@Composable
private fun SettingsScreenContent(
    settingsGlobalState: SettingsGlobalState,
    isClearProgressDialogVisible: MutableState<Boolean>,
    invokeEvent: (event: SettingsEvent) -> Unit
) {
    val uriHandler = LocalUriHandler.current
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
                    items = Language.values().toList(), onItemClicked = {
                        invokeEvent(SettingsEvent.ChangeLanguageEvent(context, it))
                    }, defaultButtonText = stringResource(settingsGlobalState.language.titleId)
                )
            }
            SettingsRowSection(label = stringResource(R.string.vibration)) {
                FishSwitch(checked = settingsGlobalState.vibration, onCheckedChange = {
                    invokeEvent(SettingsEvent.UpdateSettingsEvent(vibration = it))
                })
            }
            SettingsSliderSection(label = stringResource(R.string.music),
                value = settingsGlobalState.musicPercentage,
                onValueChange = {
                    invokeEvent(SettingsEvent.SetMusicVolumeEvent(it))
                })
            SettingsSliderSection(label = stringResource(R.string.sound),
                value = settingsGlobalState.soundPercentage,
                onValueChange = {
                    invokeEvent(SettingsEvent.UpdateSettingsEvent(soundPercentage = it))
                })
            FadingHorizontalDivider()
            SettingsRowSection(label = stringResource(R.string.neon_styles)) {
                FishSwitch(checked = settingsGlobalState.neonStyles, onCheckedChange = {
                    invokeEvent(SettingsEvent.UpdateSettingsEvent(neonStyles = it))
                })
            }
            SettingsButtonGridSection(
                onReportProblemClicked = { uriHandler.openUri(SettingsUris.reportProblem) },
                onHowToPlayClicked = { },
                onRateUsClicked = { uriHandler.openUri(SettingsUris.rateUs) },
                onSupportClicked = { uriHandler.openUri(SettingsUris.support) },
            )
            DangerOutlinedFishButton(
                onClick = { isClearProgressDialogVisible.value = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                CapText(text = stringResource(R.string.clear_progress))
            }
        }
        if (isClearProgressDialogVisible.value) {
            SettingsClearProgressDialog(onDismissRequest = {
                isClearProgressDialogVisible.value = false
            }, onAcceptClicked = {})
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    PreviewContainer {
        SettingsScreenContent(
            SettingsGlobalState(language = Language.POLISH),
            remember { mutableStateOf(false) }
        ) {}
    }
}

@Preview
@Composable
private fun SettingsScreenClearDataDialogOpenedPreview() {
    PreviewContainer {
        SettingsScreenContent(
            SettingsGlobalState(language = Language.POLISH),
            remember { mutableStateOf(true) }
        ) {}
    }
}