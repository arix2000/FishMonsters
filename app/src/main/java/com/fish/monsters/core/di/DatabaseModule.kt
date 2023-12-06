package com.fish.monsters.core.di

import com.fish.monsters.core.database.FishDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { FishDatabase.getDatabase(get()) }

    factory { get<FishDatabase>().settingsDao() }
    factory { get<FishDatabase>().contestInfoDao() }
}