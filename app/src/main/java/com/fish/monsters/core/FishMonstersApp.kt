package com.fish.monsters.core

import android.app.Application
import com.fish.monsters.core.di.appModule
import com.fish.monsters.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class FishMonstersApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FishMonstersApp)
            modules(appModule, viewModelModule)
        }
    }
}