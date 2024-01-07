package com.fish.monsters.features.safetyInformation

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

open class PreferencesManager(private val context: Context?) {
    companion object {
        val SHOW_SAFETY_SCREEN = booleanPreferencesKey("show_safety_screen")
    }

    open val showSafetyScreenFlow = context?.dataStore?.data
        ?.map { preferences ->
            preferences[SHOW_SAFETY_SCREEN] ?: true
        }

    open suspend fun setShowSafetyScreen(show: Boolean) {
        context?.dataStore?.edit { preferences ->
            preferences[SHOW_SAFETY_SCREEN] = show
        }
    }
}
