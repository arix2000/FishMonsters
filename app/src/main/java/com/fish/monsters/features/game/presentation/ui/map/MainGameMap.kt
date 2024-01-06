package com.fish.monsters.features.game.presentation.ui.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fish.monsters.common.extensions.fromOffset
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.core.MapDefaults.DEFAULT_MAP_UI_SETTINGS
import com.fish.monsters.features.game.models.Difficulty
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
fun MainGameMap(difficulty: Difficulty, userLocation: LatLng) {
    val coroutineScope = rememberCoroutineScope()
    val cameraPosition = rememberCameraPositionState(init = {
        this.position = CameraPosition.fromLatLngZoom(userLocation, 20f)
    })
    DisposableEffect(key1 = userLocation) {
        coroutineScope.launch {
            cameraPosition.animate(CameraUpdateFactory.newLatLng(userLocation))
        }
        onDispose { }
    }
    GoogleMap(
        properties = MapProperties(
            isMyLocationEnabled = true,
            mapType = MapType.SATELLITE,
        ),
        cameraPositionState = cameraPosition,
        uiSettings = DEFAULT_MAP_UI_SETTINGS,
    ) {
        MapAwardField(userLocation.fromOffset(0.0001, 0.0001))
    }
}

@Preview
@Composable
private fun MainGameMapPreview() {
    PreviewContainer(Modifier.fillMaxSize()) {
        MainGameMap(difficulty = Difficulty.HARD, userLocation = LatLng(0.0, 0.0))
    }
}