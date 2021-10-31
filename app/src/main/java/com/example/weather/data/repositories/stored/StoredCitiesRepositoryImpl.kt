package com.example.weather.data.repositories.stored

import android.content.Context
import com.example.weather.data.response.cities.CitiesFields
import com.example.weather.screens.main.MainActivityOld
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single

class StoredCitiesRepositoryImpl(context: Context): StoredCitiesRepository {

    companion object {
        val DEFAULT_VALUE = null
    }

    private val sharedPreferencesListCities =
        context.getSharedPreferences(MainActivityOld.LIST_CITIES, Context.MODE_PRIVATE)
    private val sharedPreferencesLastCity =
        context.getSharedPreferences(MainActivityOld.LAST_CITY, Context.MODE_PRIVATE)
    private val type = object : TypeToken<CitiesFields>(){}.type

    override suspend fun getListCities(): List<CitiesFields> {
        val cities = sharedPreferencesListCities.getStringSet(MainActivityOld.LIST_CITIES, HashSet<String>())
        return Single.fromCallable {
            cities?.map {
                it -> Gson().fromJson<CitiesFields>(it, type)
            }
        }
    }

    override suspend fun getLastCity(): CitiesFields {
        val city = sharedPreferencesLastCity.getString(MainActivityOld.LAST_CITY, DEFAULT_VALUE)
        return Single.fromCallable {
            Gson().fromJson<CitiesFields>(city, type)
        }
    }

}