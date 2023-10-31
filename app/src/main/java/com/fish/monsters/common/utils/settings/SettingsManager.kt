package com.fish.monsters.common.utils.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.fish.monsters.common.models.data.Settings
import com.fish.monsters.common.models.ui.Language

class SettingsManager {
    private val _state = mutableStateOf(SettingsGlobalState())
    val state: State<SettingsGlobalState> = _state

    fun updateSettings(settings: Settings) {
        _state.value = SettingsGlobalState.from(settings)
    }

    fun updateSettings(
        language: Language? = null,
        vibration: Boolean? = null,
        musicPercentage: Int? = null,
        soundPercentage: Int? = null,
        neonStyles: Boolean? = null
    ): State<SettingsGlobalState> {
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