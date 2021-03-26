package com.example.weather.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.weather.screens.main.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.Single


class CoordRepositoryImpl(private val context: Context) : CoordRepository, AppCompatActivity() {

    private lateinit var fusedLocation: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    override fun getLocation(): Single<Location> {
        fusedLocation = LocationServices.getFusedLocationProviderClient(context)

        return Single.create<Location> { emitter ->
                fusedLocation.lastLocation.addOnSuccessListener { location ->
                    emitter.onSuccess(location)
                fusedLocation.lastLocation.addOnFailureListener {
                    emitter.onError(it)
                }
            }
        }
    }


}