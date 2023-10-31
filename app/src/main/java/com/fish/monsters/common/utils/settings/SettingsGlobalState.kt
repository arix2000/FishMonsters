package com.fish.monsters.common.utils.settings

import androidx.compose.ui.text.intl.Locale
import com.fish.monsters.common.models.data.Settings
import com.fish.monsters.common.models.ui.Language

data class SettingsGlobalState(
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
        fun from(settings: Settings): SettingsGlobalState {
            return SettingsGlobalState(
                vibration = settings.vibration,
                musicPercentage = settings.musicPercentage,
                soundPercentage = settings.soundPercentage,
                neonStyles = settings.neonStyles
            )
        }
    }
}