package com.fish.monsters.core.di

import com.fish.monsters.core.navigation.Navigator
import org.koin.dsl.module

val appModule = module {
    single { Navigator() }
}