package com.example.weatherapp_fall25_ywca.UILayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp_fall25_ywca.DataLayer.WeatherData.WeatherRepo
import com.example.weatherapp_fall25_ywca.DataLayer.WeatherData.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    val weatherRepo = WeatherRepo()

    private val _weatherState= MutableStateFlow<WeatherResponse?>(null)
    val weatherState : MutableStateFlow<WeatherResponse?> = _weatherState

    fun loadWeather(cityName : String){
        viewModelScope.launch {
           val result = weatherRepo.getWeatherByName(cityName)
            weatherState.value = result
        }
    }

}