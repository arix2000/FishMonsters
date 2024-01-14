package com.fish.monsters.features.game.presentation.ui.map

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.extensions.fromOffset
import com.fish.monsters.common.extensions.getDistanceTo
import com.fish.monsters.features.game.models.Enemy
import com.fish.monsters.features.game.utils.MapConstants
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import kotlin.random.Random

@Composable
@GoogleMapComposable
fun MapEnemyField(userLocation: LatLng, enemy: Enemy, onEnemyRemoveRequest: () -> Unit) {
    val initialPosition = remember {
        userLocation.fromOffset(
            Random.nextDouble(
                MapConstants.MAP_MIN_LAT_LNG_OFFSET_RANGE.start,
                MapConstants.MAP_MAX_LAT_LNG_OFFSET_RANGE.endInclusive
            ),
            Random.nextDouble(
                MapConstants.MAP_MIN_LAT_LNG_OFFSET_RANGE.start,
                MapConstants.MAP_MAX_LAT_LNG_OFFSET_RANGE.endInclusive
            )
        )
    }
    DisposableEffect(userLocation) {
        if (userLocation.getDistanceTo(initialPosition) <= 0.000039) {
            onEnemyRemoveRequest()
        }
        onDispose { }
    }

    Circle(
        center = initialPosition,
        fillColor = enemy.colorDark,
        radius = enemy.radius,
        strokeColor = enemy.color,
        strokeWidth = 5f
    )

    MarkerComposable(
        anchor = Offset(0.5f, 0.5f),
        state = MarkerState(
            position = initialPosition
        )
    ) {
        Icon(
            painter = painterResource(enemy.icon),
            contentDescription = "",
            tint = enemy.color,
            modifier = Modifier.size(24.dp)
        )
    }
}