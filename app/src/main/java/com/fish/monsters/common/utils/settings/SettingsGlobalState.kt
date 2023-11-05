package com.fish.monsters.common.utils.settings

import com.fish.monsters.common.models.ui.Language
import com.fish.monsters.common.models.ui.getAppCurrentLanguage
import com.fish.monsters.core.database.entities.Settings

data class SettingsGlobalState(
    val language: Language = getAppCurrentLanguage(),
    val vibration: Boolean = true,
    val musicPercentage: Int = 100,
    val soundPercentage: Int = 100,
    val neonStyles: Boolean = false
) {
    fun toSettings(): Settings {
        return Settings(
            vibration = vibration,
            musicPercentage = musicPercentage,
            soundPercentage = soundPercentage,
            neonStyles = neonStyles
        )
    }

    companion object {
        fun from(settings: Settings?): SettingsGlobalState {
            return if (settings != null)
                SettingsGlobalState(
                    language = getAppCurrentLanguage(),
                    vibration = settings.vibration,
                    musicPercentage = settings.musicPercentage,
                    soundPercentage = settings.soundPercentage,
                    neonStyles = settings.neonStyles
                )
            else from(Settings.default())
        }
    }
}