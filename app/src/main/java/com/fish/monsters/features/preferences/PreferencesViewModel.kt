package com.fish.monsters.features.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class PreferencesViewModel(private val dataStore: DataStore<Preferences>) : ViewModel(),
    KoinComponent {

    companion object {
        val SHOW_SAFETY_SCREEN = booleanPreferencesKey("show_safety_screen")
    }

    val shouldShowSafetyScreen: Flow<Boolean> = this.dataStore.data
        .map { preferences ->
            preferences[SHOW_SAFETY_SCREEN] ?: true
        }

    fun setShowSafetyScreen(show: Boolean) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[SHOW_SAFETY_SCREEN] = show
            }
        }
    }
}
