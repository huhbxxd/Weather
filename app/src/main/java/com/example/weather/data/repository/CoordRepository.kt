package com.example.weather.data.repository

import android.location.Location
import io.reactivex.Single

interface CoordRepository {

    fun getLocation(): Single<Location>
}