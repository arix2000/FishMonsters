package com.fish.monsters.core

import com.google.maps.android.compose.MapUiSettings

object SettingsUris {
    const val rateUs = "https://play.google.com/store/apps/details?id=com.shams.futurecity"
    const val reportProblem = "https://github.com/arix2000/FishMonsters/issues/new"
    const val support = "https://patronite.pl/RatujRyby"
}

object MapDefaults {
    val DEFAULT_MAP_UI_SETTINGS = MapUiSettings(
        compassEnabled = true,
        indoorLevelPickerEnabled = true,
        mapToolbarEnabled = false,
        myLocationButtonEnabled = false,
        rotationGesturesEnabled = false,
        scrollGesturesEnabled = false,
        scrollGesturesEnabledDuringRotateOrZoom = false,
        tiltGesturesEnabled = false,
        zoomControlsEnabled = false,
        zoomGesturesEnabled = false,
    )
}