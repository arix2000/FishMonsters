package com.fish.monsters.features.game.presentation.ui.map

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.TextColorDarkA80
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState

@Composable
fun MapAwardField(position: LatLng) {
    Circle(
        center = position,
        fillColor = TextColorDarkA80,
        radius = 20.0,
        strokeColor = DarkPrimaryColor
    )
    MarkerComposable(
        anchor = Offset(0.5f, 0.5f),
        state = MarkerState(
            position = position
        )
    ) {
        Icon(
            imageVector = Icons.Default.AccessTimeFilled,
            contentDescription = "",
            tint = DarkPrimaryColor
        )
    }
}

@Preview
@Composable
private fun MapAwardFieldPreview() {
    PreviewContainer {
        MapAwardField(LatLng(0.0, 0.0))
    }
}