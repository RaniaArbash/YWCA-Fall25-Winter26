package com.example.weatherapp_fall25_ywca.DataLayer.WeatherData

data class WeatherResponse(
    val name: String,
    val weather : List<WeatherObject> ,
    val main : MainObject
    )

data class MainObject(val temp: Double, val feels_like: Double, val humidity: Int)
data class WeatherObject(val description: String, val icon: String)