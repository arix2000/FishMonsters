package com.fish.monsters.core.di

import android.media.MediaPlayer
import com.fish.monsters.common.utils.MusicManager
import com.fish.monsters.common.utils.SoundsManager
import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.features.settings.data.SettingsManager
import org.koin.dsl.module

val appModule = module {
    single { Navigator() }

    single { SettingsManager() }

    factory { MediaPlayer() }

    single { MusicManager(get(), get()) }

    factory { SoundsManager(get(), get()) }
}