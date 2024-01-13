package com.fish.monsters.features.game.presentation

sealed class MainGameEvent {
    data object ListenOnUserLocationChanges :
        MainGameEvent()

    data object ListenOnTime: MainGameEvent()
}