package com.fish.monsters.features.game.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.fish.monsters.common.extensions.hasLocationPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LocationService(
    private val context: Context,
    private val locationClient: FusedLocationProviderClient
) {
    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(): Flow<LatLng?> = callbackFlow {
        if (!context.hasLocationPermission()) {
            trySend(null)
            return@callbackFlow
        }

        val request = LocationRequest.Builder(1000L)
            .setIntervalMillis(1000L)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let {
                    trySend(LatLng(it.latitude, it.longitude))
                }
            }
        }

        locationClient.requestLocationUpdates(
            request,
            locationCallback,
            Looper.getMainLooper()
        )

        awaitClose {
            locationClient.removeLocationUpdates(locationCallback)
        }
    }
}