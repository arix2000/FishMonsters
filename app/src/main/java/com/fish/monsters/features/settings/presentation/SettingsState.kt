package com.fish.monsters.features.settings.presentation

import androidx.compose.ui.text.intl.Locale
import com.fish.monsters.common.models.ui.Language
import com.fish.monsters.features.settings.data.Settings

data class SettingsState(
    val language: Language = Language.values().first { it.code == Locale.current.language },
    val vibration: Boolean = true,
    val musicPercentage: Int = 100,
    val soundPercentage: Int = 100,
    val neonStyles: Boolean = false
) {
    fun toSettings(): Settings {
        return Settings(vibration, musicPercentage, soundPercentage, neonStyles)
    }

    companion object {
        fun from(settings: Settings): SettingsState {
            return SettingsState(
                vibration = settings.vibration,
                musicPercentage = settings.musicPercentage,
                soundPercentage = settings.soundPercentage,
                neonStyles = settings.neonStyles
            )
        }
    }
}