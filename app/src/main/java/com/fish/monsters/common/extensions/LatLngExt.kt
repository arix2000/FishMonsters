package com.fish.monsters.common.extensions

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

fun LatLng.getDistanceTo(otherPoint: LatLng) = sqrt(
    (otherPoint.latitude - this.latitude).pow(2) + (otherPoint.longitude - this.longitude).pow(2)
)