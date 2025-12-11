package com.example.weatherapp_fall25_ywca.DataLayer.RoomDatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp_fall25_ywca.UILayer.FavCityUI.FavCityViewModel


class CityViewModelFactory(private val repository: CityRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavCityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavCityViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


