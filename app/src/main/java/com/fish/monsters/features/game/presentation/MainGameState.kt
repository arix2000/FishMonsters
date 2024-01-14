package com.fish.monsters.features.game.presentation

import com.google.android.gms.maps.model.LatLng

data class MainGameState(
    val isLoading: Boolean = false,
    val userLocation: LatLng? = null,
    val timeSeconds: Int = 0,
    val points: Int = 0
)