package com.fish.monsters.core.di

import android.content.Context
import android.media.MediaPlayer
import com.fish.monsters.common.utils.MusicManager
import com.fish.monsters.common.utils.SoundsManager
import com.fish.monsters.common.utils.settings.SettingsManager
import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.features.game.utils.LocationService
import com.google.android.gms.location.LocationServices
import org.koin.dsl.module

val appModule = module {
    single { Navigator() }

    single { SettingsManager(settingsDao = get()) }

    factory { MediaPlayer() }

    single { MusicManager(get(), get()) }

    factory { SoundsManager(get(), get()) }

    factory { LocationService(get(), LocationServices.getFusedLocationProviderClient(get<Context>())) }
}