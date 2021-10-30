package com.example.weather.core.network.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        const val API_READ_TIME_OUT = 10L
        const val API_CONNECT_TIME_OUT = 10L
    }
}