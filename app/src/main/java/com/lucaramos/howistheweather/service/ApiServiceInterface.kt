package com.lucaramos.howistheweather.service

import com.lucaramos.howistheweather.model.City
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("weather")
    fun getCityWeather(@Query("q") cityName: String,
                     @Query("APPID") appId: String) : Call<City>
}