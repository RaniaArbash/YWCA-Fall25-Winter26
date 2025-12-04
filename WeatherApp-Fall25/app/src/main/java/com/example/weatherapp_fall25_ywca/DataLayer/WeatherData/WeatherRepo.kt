package com.example.weatherapp_fall25_ywca.DataLayer.WeatherData

class WeatherRepo {
    suspend fun getWeatherByName(cityName: String): WeatherResponse {
        return WeatherApiService.weather_api.getWeather(cityName)
    }

    suspend fun getWeatherByLocation(lat: Double, lon: Double): WeatherResponse {
        return WeatherApiService.weather_api.getWeatherByLocation(lat,lon)
    }
}