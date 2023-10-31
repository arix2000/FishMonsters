package com.fish.monsters.features.settings

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.State
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import com.fish.monsters.common.models.ui.Language
import com.fish.monsters.common.utils.MusicManager
import com.fish.monsters.common.utils.settings.SettingsGlobalState
import com.fish.monsters.common.utils.settings.SettingsManager
import com.fish.monsters.features.settings.ui.SettingsEvent

class SettingsViewModel(
    private val settingsManager: SettingsManager, private val musicManager: MusicManager
) : ViewModel() {

    val settingsGlobalState: State<SettingsGlobalState> = settingsManager.state

    fun invokeEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ChangeLanguageEvent -> changeLanguage(event.context, event.language)
            is SettingsEvent.SetMusicVolumeEvent -> setMusicVolume(event.percents)
            is SettingsEvent.UpdateSettingsEvent -> {
                with(event) {
                    updateSettings(
                        language, vibration, musicPercentage, soundPercentage, neonStyles
                    )
                }
            }
        }
    }

    private fun changeLanguage(context: Context, language: Language) {
        settingsManager.updateSettings(language = language)
        context.findActivity()?.runOnUiThread {
            val appLocale = LocaleListCompat.forLanguageTags(language.code)
            AppCompatDelegate.setApplicationLocales(appLocale)
        }
    }

    private fun Context.findActivity(): Activity? = when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }

    private fun setMusicVolume(percents: Int) {
        updateSettings(musicPercentage = percents)
        musicManager.setVolume(percents)
    }

    private fun updateSettings(
        language: Language? = null,
        vibration: Boolean? = null,
        musicPercentage: Int? = null,
        soundPercentage: Int? = null,
        neonStyles: Boolean? = null
    ) {
        settingsManager.updateSettings(
            language, vibration, musicPercentage, soundPercentage, neonStyles
        ).value
    }
}