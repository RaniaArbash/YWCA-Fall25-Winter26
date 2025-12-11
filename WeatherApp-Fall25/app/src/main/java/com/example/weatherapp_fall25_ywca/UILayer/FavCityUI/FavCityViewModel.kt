package com.example.weatherapp_fall25_ywca.UILayer.FavCityUI

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp_fall25_ywca.DataLayer.RoomDatabase.City
import com.example.weatherapp_fall25_ywca.DataLayer.RoomDatabase.CityRepository
import com.example.weatherapp_fall25_ywca.DataLayer.WeatherData.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.launch


class FavCityViewModel(private val repo: CityRepository): ViewModel() {

    // state
    private var _favoritesCities= MutableStateFlow<List<City>?>(emptyList())
    val favoritesCities : MutableStateFlow<List<City>??> = _favoritesCities


    fun saveCity( name: String, lat: Double, lon: Double){
        viewModelScope.launch {
            repo.addNewCityTODB(City(0,name,lat,lon))
        }
    }

    fun getAllSavedCities(){
        viewModelScope.launch {
            _favoritesCities.value =
                repo.getFavCities() as List<City>? //.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
        }
    }

    fun deleteCity(city: City){
        viewModelScope.launch {
            repo.deleteOneCity(city)
        }
    }

    fun searchForCIty(text: String){
        viewModelScope.launch {
            _favoritesCities.value = repo.searchInDB(text)
        }
    }


}