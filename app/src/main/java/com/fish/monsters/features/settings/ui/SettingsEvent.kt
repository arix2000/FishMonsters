package com.fish.monsters.features.settings.ui

import android.content.Context
import com.fish.monsters.common.models.ui.Language

sealed class SettingsEvent {

    class SetMusicVolumeEvent(val percents: Int) : SettingsEvent()

    class ChangeLanguageEvent(val context: Context, val language: Language) : SettingsEvent()

    class UpdateSettingsEvent(
        val language: Language? = null,
        val vibration: Boolean? = null,
        val musicPercentage: Int? = null,
        val soundPercentage: Int? = null,
        val neonStyles: Boolean? = null
    ) : SettingsEvent()

    object DeleteAllData: SettingsEvent()

    object SaveCurrentSettings: SettingsEvent()

}