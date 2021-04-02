package com.example.weather.data

import com.example.weather.data.cities.Cities
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CitiesApi {

    @GET("/api/records/1.0/search/")
    fun getCities(
        @Query("dataset") dataset: String,
        @Query("q") query: String,
        @Query("start") page: Int,
        @Query("rows") rows: Int
    ): Single<Cities>

}