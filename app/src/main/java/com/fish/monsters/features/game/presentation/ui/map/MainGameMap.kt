package com.fish.monsters.features.game.presentation.ui.map

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsScore
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fish.monsters.common.extensions.toReadableTime
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.core.MapDefaults
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.TextColorDark
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
    onGameOver: () -> Unit,
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
    Box {
        GoogleMap(
            properties = MapProperties(
                mapType = MapType.SATELLITE,
                isMyLocationEnabled = true
            ),
            cameraPositionState = cameraPosition,
            uiSettings = MapDefaults.DEFAULT_MAP_UI_SETTINGS,
        ) {
            MapAwardFieldsUiManager(
                state,
                difficulty,
                onAwardEarned = { invokeEvent(MainGameEvent.AddPoints(it.points)) })
            MapEnemyFieldsUiManager(state, difficulty, onCaughtByEnemy = onGameOver)
        }
        val shape = PartiallyCutCornerShape(DpSize(5.dp, 10.dp))
        Box(
            modifier = Modifier
                .padding(7.dp)
                .background(TextColorDark, shape)
                .border(1.dp, DarkPrimaryColor, shape)
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(text = state.timeSeconds.toReadableTime(), fontSize = 20.sp)
        }
        Box(
            modifier = Modifier
                .padding(7.dp)
                .background(TextColorDark, shape)
                .border(1.dp, DarkPrimaryColor, shape)
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .align(Alignment.BottomEnd)
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.SportsScore,
                    contentDescription = "score",
                    tint = DarkPrimaryColor
                )
                Text(text = state.points.toString(), fontSize = 20.sp)
            }
        }
    }
}