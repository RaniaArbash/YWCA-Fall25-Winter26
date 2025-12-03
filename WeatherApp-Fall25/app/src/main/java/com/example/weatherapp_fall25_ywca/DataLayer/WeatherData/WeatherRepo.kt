package com.example.weatherapp_fall25_ywca.DataLayer.WeatherData

class WeatherRepo {
    suspend fun getWeatherByName(cityName: String): WeatherResponse {
        return WeatherApiService.weather_api.getWeather(cityName)
    }
}