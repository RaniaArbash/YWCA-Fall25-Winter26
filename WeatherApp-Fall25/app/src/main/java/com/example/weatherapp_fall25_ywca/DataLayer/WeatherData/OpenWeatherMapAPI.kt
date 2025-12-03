package com.example.weatherapp_fall25_ywca.DataLayer.WeatherData

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapAPI {

    @GET("weather")
    suspend fun getWeather(@Query("q") query: String,
                           @Query(value = "appid") apikey: String = "ecf5553cc5b15522aea8026824cb8085",
                           @Query(value = "units") units: String = "metric"
                           ): WeatherResponse

}