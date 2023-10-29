package com.fish.monsters.core.di

import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.features.settings.data.SettingsManager
import org.koin.dsl.module

val appModule = module {
    single { Navigator() }

    single { SettingsManager() }
}