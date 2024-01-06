package com.fish.monsters.common.extensions

import com.google.android.gms.maps.model.LatLng

fun LatLng.fromOffset(latitude: Double, longitude: Double): LatLng {
    return LatLng(this.latitude + latitude, this.longitude + longitude)
}