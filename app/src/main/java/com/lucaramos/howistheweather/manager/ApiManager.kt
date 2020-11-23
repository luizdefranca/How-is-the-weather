package com.lucaramos.howistheweather.manager

import com.lucaramos.howistheweather.service.ApiServiceInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    private val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    private val instance = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun apiService() = instance.create(ApiServiceInterface::class.java)
}
