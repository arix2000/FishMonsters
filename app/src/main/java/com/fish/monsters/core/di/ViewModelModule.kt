package com.fish.monsters.core.di

import com.fish.monsters.features.history.HistoryViewModel
import com.fish.monsters.features.preferences.PreferencesViewModel
import com.fish.monsters.features.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SettingsViewModel(get(), get(), get(), get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { PreferencesViewModel(get()) }
}