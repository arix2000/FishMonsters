package com.fish.monsters.features.settings.data

import androidx.compose.ui.text.intl.Locale

data class SettingsState(
    val language: String = Locale.current.language,
    val vibration: Boolean = true,
    val musicPercentage: Int = 100,
    val soundPercentage: Int = 100,
    val neonStyles: Boolean = false
) {
    companion object {
        fun from(settings: Settings): SettingsState {
            return SettingsState(
                settings.language,
                settings.vibration,
                settings.musicPercentage,
                settings.soundPercentage,
                settings.neonStyles
            )
        }
    }
}