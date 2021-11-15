package com.example.weather.core.network.di

import com.example.weather.data.api.CitiesApi
import com.example.weather.data.api.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun providerOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val httpUrl = it.request().url()
                val requestBuilder = it.request().newBuilder().url(httpUrl)
                it.proceed(requestBuilder.build())
            }
            .readTimeout(API_READ_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(API_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit.Builder): WeatherApi {
        return retrofit
            .baseUrl(WEATHER_URL)
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCitiesApi(retrofit: Retrofit.Builder): CitiesApi {
        return retrofit
            .baseUrl(CITIES_URL)
            .build()
            .create(CitiesApi::class.java)
    }

    companion object {
        const val WEATHER_URL = "https://api.openweathermap.org"
        const val CITIES_URL = "https://public.opendatasoft.com/"
        const val API_READ_TIME_OUT = 10L
        const val API_CONNECT_TIME_OUT = 10L
    }
}