package com.example.weather.data.repositories.coord

import android.location.Location
import io.reactivex.Single

interface CoordRepository {

    fun getLocation(): Single<Location>
}