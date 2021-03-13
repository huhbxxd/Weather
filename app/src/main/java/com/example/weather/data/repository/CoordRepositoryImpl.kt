package com.example.weather.data.repository

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import io.reactivex.Single


class CoordRepositoryImpl(context: Application) : CoordRepository {

    private val fusedLocation = FusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")

    override fun getLocation(): Single<Location> {
        return Single.create { emitter ->
            fusedLocation.lastLocation.addOnSuccessListener { location ->
                emitter.onSuccess(location)
            }
        }
    }
}