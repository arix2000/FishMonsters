package com.fish.monsters.common.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.fish.monsters.common.utils.SoundsManager
import com.fish.monsters.common.utils.settings.SettingsManager

@Composable
fun previewGetSoundsManager(): SoundsManager {
    return SoundsManager(LocalContext.current, SettingsManager())
}