package com.example.weather.data.repositories.location

import android.location.Location
import io.reactivex.Single

interface LocationRepository {

    fun getLocation(): Single<Location>
}