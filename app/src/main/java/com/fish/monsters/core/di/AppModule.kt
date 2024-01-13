package com.fish.monsters.core.di

import android.media.MediaPlayer
import com.fish.monsters.common.utils.MusicManager
import com.fish.monsters.common.utils.SoundsManager
import com.fish.monsters.common.utils.settings.SettingsManager
import com.fish.monsters.core.database.dataStore.dataStore
import com.fish.monsters.core.navigation.Navigator
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { Navigator() }

    single { SettingsManager(settingsDao = get()) }

    factory { MediaPlayer() }

    single { MusicManager(get(), get()) }

    factory { SoundsManager(get(), get()) }

    single { androidContext().dataStore }
}