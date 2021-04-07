package com.example.weather.data.repositories.list

import android.content.Context
import android.content.SharedPreferences
import com.example.weather.data.cities.CitiesFields
import com.example.weather.screens.main.MainActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single

class ListCitiesRepositoryImpl(context: Context): ListCitiesRepository {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(MainActivity.LIST_CITIES, Context.MODE_PRIVATE)
    private val type = object : TypeToken<CitiesFields>(){}.type

    override fun getListCities(): Single<List<CitiesFields>> {
        val city = sharedPreferences.getStringSet(MainActivity.LIST_CITIES, HashSet<String>())
        return Single.fromCallable {
            city?.map {
                it -> Gson().fromJson<CitiesFields>(it, type)
            }

        }
    }

}