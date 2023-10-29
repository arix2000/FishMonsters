package com.fish.monsters.features.settings.data

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class SettingsManager {
    private val _state = mutableStateOf(SettingsState())
    val state: State<SettingsState> = _state

    fun updateTempSettings(settings: Settings) {
        _state.value = SettingsState.from(settings)
    }

    fun updateTempSettings(
        language: String? = null,
        vibration: Boolean? = null,
        musicPercentage: Int? = null,
        soundPercentage: Int? = null,
        neonStyles: Boolean? = null
    ): State<SettingsState> {
        _state.run {
            language?.let { value = value.copy(language = language) }
            vibration?.let { value = value.copy(vibration = vibration) }
            musicPercentage?.let {
                value = value.copy(musicPercentage = musicPercentage)
            }
            soundPercentage?.let {
                value = value.copy(soundPercentage = soundPercentage)
            }
            neonStyles?.let { value = value.copy(neonStyles = neonStyles) }
        }
        return state
    }
}