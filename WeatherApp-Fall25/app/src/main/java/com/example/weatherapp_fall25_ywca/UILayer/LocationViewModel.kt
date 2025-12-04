package com.example.weatherapp_fall25_ywca.UILayer

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp_fall25_ywca.DataLayer.LocationData.LocationService
import com.example.weatherapp_fall25_ywca.DataLayer.WeatherData.WeatherRepo
import com.example.weatherapp_fall25_ywca.DataLayer.WeatherData.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationViewModel(application: Application):
    AndroidViewModel(application) {

    // state var
    private val _currentLocation = MutableStateFlow<Location?>(null)
    val currentLocation: StateFlow<Location?> = _currentLocation

    var locationService = LocationService(application)


    private val _weatherLocationState= MutableStateFlow<WeatherResponse?>(null)
    val weatherLocationState : MutableStateFlow<WeatherResponse?> = _weatherLocationState


    var weatherRepo = WeatherRepo()
    @androidx.annotation.RequiresPermission(allOf = [android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION])
    fun fetchLocation(){
        viewModelScope.launch  {
            try {
                val location = locationService.getCurrentLocation()
                _currentLocation.value = location
            }catch (e: Exception) {
                _currentLocation.value = null
            }
        }

    }

    fun fetchWeatherForLocation(lat: Double, lon: Double){
        viewModelScope.launch  {
            weatherLocationState.value =  weatherRepo.getWeatherByLocation(lat,lon)
        }
    }

}