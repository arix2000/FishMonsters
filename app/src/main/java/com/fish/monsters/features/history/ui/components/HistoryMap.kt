package com.fish.monsters.features.history.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.fish.monsters.R
import com.fish.monsters.common.extensions.drawNeonStroke
import com.fish.monsters.common.extensions.isPreview
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.common.utils.settings.SettingsManager
import com.fish.monsters.common.views.buttons.FishButton
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.DarkPrimaryColorA12
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import org.koin.compose.koinInject

@Composable
fun HistoryMap(
    gameLocation: LatLng,
    shape: PartiallyCutCornerShape = PartiallyCutCornerShape(
        DpSize(12.dp, 31.dp),
    ),
    neonStyle: Boolean = if (isPreview()) false else koinInject<SettingsManager>().state.value.neonStyles,
) {
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(gameLocation, 15f)
    }
    Box(
        modifier = Modifier
            .height(153.dp)
            .fillMaxWidth()
            .border(BorderStroke(1.dp, DarkPrimaryColor), shape = shape)
            .drawWithContent {
                drawContent()
                if (neonStyle)
                    drawNeonStroke(DpSize(12.dp, 31.dp), DarkPrimaryColor)
            }
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
                    context,
                    R.raw.map_style
                )
            ),
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
                scrollGesturesEnabled = false,
                zoomGesturesEnabled = false,
                myLocationButtonEnabled = false,
                compassEnabled = false,
                mapToolbarEnabled = false,
                rotationGesturesEnabled = false,
                tiltGesturesEnabled = false
            ),
        ) {
            Marker(
                state = MarkerState(position = gameLocation),
                icon = remember {
                    BitmapDescriptorFactory.fromResource(R.drawable.marker_icon)
                },
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
                .background(DarkPrimaryColorA12)
                .clickable {
                    openGoogleMaps(
                        gameLocation.latitude,
                        gameLocation.longitude,
                        context
                    )
                }
        )
        FishButton(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .height(29.dp)
                .align(Alignment.BottomCenter),
            contentPadding = PaddingValues(vertical = 0.dp, horizontal = 15.dp),
            indentationSize = DpSize(10.dp, 12.dp),
            onClick = { openGoogleMaps(gameLocation.latitude, gameLocation.longitude, context) },
        ) {
            Text(
                text = stringResource(id = R.string.open_google_maps).uppercase(),
                fontSize = 14.sp
            )
        }
    }
}

fun openGoogleMaps(latitude: Double, longitude: Double, context: Context) {
    val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")

    startActivity(context, mapIntent, null)
}
