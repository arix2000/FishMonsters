package com.fish.monsters.features.game.presentation.ui.map

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.fish.monsters.core.MapDefaults.DEFAULT_MAP_UI_SETTINGS
import com.fish.monsters.features.game.models.Difficulty
import com.fish.monsters.features.game.presentation.MainGameEvent
import com.fish.monsters.features.game.presentation.MainGameState
import com.fish.monsters.features.game.presentation.ui.managers.MapAwardFieldsUiManager
import com.fish.monsters.features.game.presentation.ui.managers.MapEnemyFieldsUiManager
import com.fish.monsters.features.game.utils.MapConstants.DEFAULT_ZOOM
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
fun MainGameMap(
    difficulty: Difficulty,
    state: MainGameState,
    invokeEvent: (event: MainGameEvent) -> Unit
) {
    val userLocation = state.userLocation!!
    val coroutineScope = rememberCoroutineScope()
    val cameraPosition = rememberCameraPositionState(init = {
        this.position = CameraPosition.fromLatLngZoom(userLocation, DEFAULT_ZOOM)
    })
    LaunchedEffect(true) {
        invokeEvent(MainGameEvent.ListenOnTime)
    }
    DisposableEffect(key1 = state) {
        coroutineScope.launch {
            cameraPosition.animate(CameraUpdateFactory.newLatLngZoom(userLocation, DEFAULT_ZOOM))
        }
        onDispose { }
    }
    DisposableEffect(key1 = state.timeSeconds) {
        Log.d("TIME_IN_SECONDS", state.timeSeconds.toString())
        onDispose {  }
    }
    GoogleMap(
        properties = MapProperties(
            mapType = MapType.SATELLITE,
            isMyLocationEnabled = true
        ),
        cameraPositionState = cameraPosition,
        uiSettings = DEFAULT_MAP_UI_SETTINGS,
    ) {
        MapAwardFieldsUiManager(state, difficulty)
        MapEnemyFieldsUiManager(state, difficulty)
    }
}