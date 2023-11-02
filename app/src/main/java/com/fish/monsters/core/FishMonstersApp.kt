package com.fish.monsters.core

import android.app.Application
import com.fish.monsters.common.utils.MusicManager
import com.fish.monsters.common.utils.settings.SettingsManager
import com.fish.monsters.core.di.appModule
import com.fish.monsters.core.di.databaseModule
import com.fish.monsters.core.di.viewModelModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class FishMonstersApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FishMonstersApp)
            modules(appModule, viewModelModule, databaseModule)
        }
        val settingsManager: SettingsManager = get()
        settingsManager.initSettingsFromDatabase().invokeOnCompletion {
            val musicManager: MusicManager = get()
            musicManager.setVolume(settingsManager.state.value.musicPercentage)
        }
    }
}