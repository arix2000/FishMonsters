package com.fish.monsters.common.extensions

import android.location.Location
import com.google.android.gms.maps.model.LatLng
import kotlin.math.pow
import kotlin.math.sqrt

fun LatLng.fromOffset(latitude: Double, longitude: Double): LatLng {
    return LatLng(this.latitude + latitude, this.longitude + longitude)
}

/**
 * It takes two arguments, [point] and [offset].
 *
 * [point] - The point in the direction in which [this] point will be moved by the given [offset].
 *
 * @return [LatLng] which is new point moved by [offset].
 **/
fun LatLng.moveInDirectionOf(point: LatLng, offset: Double): LatLng {
    val distance =
        sqrt((point.latitude - this.latitude).pow(2) + (point.longitude - this.longitude).pow(2))

    val newLatitude = this.latitude + offset * (point.latitude - this.latitude) / distance
    val newLongitude = this.longitude + offset * (point.longitude - this.longitude) / distance

    return LatLng(newLatitude, newLongitude)
}

fun LatLng.getDistanceTo(otherPoint: LatLng): Float {
    val results = FloatArray(1)
    Location.distanceBetween(
        this.latitude, this.longitude,
        otherPoint.latitude, otherPoint.longitude,
        results
    )
    return results[0]
}