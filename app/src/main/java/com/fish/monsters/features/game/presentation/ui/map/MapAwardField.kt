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
import com.fish.monsters.core.database.entities.contest.AwardType
import com.fish.monsters.core.database.entities.contest.getIcon
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.TextColorDarkA80
import com.fish.monsters.features.game.utils.MapConstants.MAP_MAX_LAT_LNG_OFFSET_RANGE
import com.fish.monsters.features.game.utils.MapConstants.MAP_MIN_LAT_LNG_OFFSET_RANGE
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import kotlin.random.Random

@Composable
@GoogleMapComposable
fun MapAwardField(userLocation: LatLng, type: AwardType, onAwardRemoveRequest: () -> Unit) {
    val initialPosition = remember {
        userLocation.fromOffset(
            Random.nextDouble(
                MAP_MIN_LAT_LNG_OFFSET_RANGE.start,
                MAP_MAX_LAT_LNG_OFFSET_RANGE.endInclusive
            ),
            Random.nextDouble(
                MAP_MIN_LAT_LNG_OFFSET_RANGE.start,
                MAP_MAX_LAT_LNG_OFFSET_RANGE.endInclusive
            )
        )
    }
    DisposableEffect(userLocation) {
        if (userLocation.getDistanceTo(initialPosition) <= 0.000039) {
            onAwardRemoveRequest()
        }
        onDispose {  }
    }

    Circle(
        center = initialPosition,
        fillColor = TextColorDarkA80,
        radius = 4.0,
        strokeColor = DarkPrimaryColor,
        strokeWidth = 5f
    )
    MarkerComposable(
        anchor = Offset(0.5f, 0.5f),
        state = MarkerState(
            position = initialPosition
        )
    ) {
        Icon(
            painter = painterResource(type.getIcon()),
            contentDescription = "",
            tint = DarkPrimaryColor,
            modifier = Modifier.size(24.dp)
        )
    }
}
