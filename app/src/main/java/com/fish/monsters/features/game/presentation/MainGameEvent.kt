package com.fish.monsters.features.game.presentation

sealed class MainGameEvent {
    object ListenOnUserLocationChanges :
        MainGameEvent()
}