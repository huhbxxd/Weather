package com.example.weather.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.*
import io.reactivex.Single


class CoordRepositoryImpl(private val context: Context) : CoordRepository {

    private companion object {
        val DISTANCE = 5000 // in meters
    }

    private lateinit var fusedLocation: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    @SuppressLint("MissingPermission")
    override fun getLocation(): Single<Location> {
        fusedLocation = LocationServices.getFusedLocationProviderClient(context)
        return Single.create<Location> { emitter ->
                fusedLocation.lastLocation.addOnSuccessListener { location ->
                    if (location == null || location.accuracy > DISTANCE) {
                        locationCallback = object : LocationCallback() {
                            override fun onLocationResult(locationResult: LocationResult?) {
                                locationResult ?: return
                                for (location in locationResult.locations) {
                                    emitter.onSuccess(location)
                                }
                            }
                        }
                        fusedLocation.requestLocationUpdates(locationRequest(), locationCallback, null)
                        emitter.onSuccess(location)
                        stopLocationUpdate()
                    } else {
                        emitter.onSuccess(location)
                    }
            }
        }
    }

    private fun locationRequest(): LocationRequest? {
        return LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private fun stopLocationUpdate() {
        fusedLocation.removeLocationUpdates(locationCallback)
    }


}