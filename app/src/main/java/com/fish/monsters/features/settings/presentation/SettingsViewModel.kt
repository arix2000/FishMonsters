package com.fish.monsters.features.settings.presentation

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import com.fish.monsters.common.models.ui.Language
import com.fish.monsters.common.utils.MusicManager
import com.fish.monsters.features.settings.data.SettingsManager
import com.fish.monsters.features.settings.data.SettingsState

class SettingsViewModel(
    private val settingsManager: SettingsManager,
    private val musicManager: MusicManager
) : ViewModel() {

    private val _state = mutableStateOf(settingsManager.state.value)
    val state: State<SettingsState> = _state

    fun changeLanguage(context: Context, language: Language) {
        settingsManager.updateTempSettings(language = language)
        context.findActivity()?.runOnUiThread {
            val appLocale = LocaleListCompat.forLanguageTags(language.code)
            AppCompatDelegate.setApplicationLocales(appLocale)
        }
    }

    fun setMusicVolume(percents: Int) {
        updateSettings(musicPercentage = percents)
        musicManager.setVolume(percents)
    }

    fun updateSettings(
        language: Language? = null,
        vibration: Boolean? = null,
        musicPercentage: Int? = null,
        soundPercentage: Int? = null,
        neonStyles: Boolean? = null
    ) {
        _state.value = settingsManager.updateTempSettings(
            language,
            vibration,
            musicPercentage,
            soundPercentage,
            neonStyles
        ).value
    }

    private fun Context.findActivity(): Activity? = when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }
}