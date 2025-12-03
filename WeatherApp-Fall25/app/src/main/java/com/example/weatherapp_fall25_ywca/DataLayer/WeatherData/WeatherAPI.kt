package com.example.weatherapp_fall25_ywca.DataLayer.WeatherData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiService { // creating the object
    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    val weather_api : OpenWeatherMapAPI by lazy {
        Retrofit.Builder().
        baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create())
            .build().create(OpenWeatherMapAPI::class.java)
    }
}
