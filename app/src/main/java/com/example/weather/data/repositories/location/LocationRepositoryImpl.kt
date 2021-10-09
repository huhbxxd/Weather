package com.example.weather.data.repositories.location

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import io.reactivex.Single
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext


class LocationRepositoryImpl(private val context: Context) : LocationRepository, AppCompatActivity() {

    private lateinit var fusedLocation: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    override fun onResume() {
        super.onResume()
        if (ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) startLocationUpdate()
    }

    @SuppressLint("MissingPermission")
    override fun getLocation(): Single<Location> {
        fusedLocation = LocationServices.getFusedLocationProviderClient(context)
//        flow<Location> {
//            fusedLocation.lastLocation.addOnSuccessListener { location ->
//                    if (location == null || location.accuracy > DISTANCE) {
//                        locationCallback = object : LocationCallback() {
//                            override fun onLocationResult(locationResult: LocationResult?) {
//                                locationResult ?: return
//                                for (it in locationResult.locations) {
//                                    emit(it)
//                                }
//                            }
//                        }
//                    } else {
//                        emit(location)
//                    }
//            }
//        }
        return Single.create<Location> { emitter ->
                fusedLocation.lastLocation.addOnSuccessListener { location ->
                    if (location == null || location.accuracy > DISTANCE) {
                        locationCallback = object : LocationCallback() {
                            override fun onLocationResult(locationResult: LocationResult?) {
                                locationResult ?: return
                                for (it in locationResult.locations) {
                                    emitter.onSuccess(location)
                                }
                            }
                        }
                    } else {
                        emitter.onSuccess(location)
                    }
            }
        }
    }

    private fun locationRequest(): LocationRequest? {
        return LocationRequest.create()?.apply {
            interval = INTERVAL
            fastestInterval = FASTEST_INTERVAL
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private fun stopLocationUpdate() {
        fusedLocation.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdate() {
        fusedLocation.requestLocationUpdates(locationRequest(),
            locationCallback,
            Looper.getMainLooper())
    }

    override fun onStop() {
        super.onStop()
        stopLocationUpdate()
    }

    companion object {
        private const val DISTANCE = 5000 // in meters
        private const val INTERVAL = 10000L
        private const val FASTEST_INTERVAL = 5000L
    }

}