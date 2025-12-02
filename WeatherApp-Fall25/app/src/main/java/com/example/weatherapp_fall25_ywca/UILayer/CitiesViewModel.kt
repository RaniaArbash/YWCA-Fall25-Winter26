package com.example.weatherapp_fall25_ywca.UILayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp_fall25_ywca.DataLayer.CitiesRepo
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class CitiesViewModel : ViewModel(){

    private val repo = CitiesRepo()

    var cities = MutableStateFlow<List<String>>(emptyList())
    var searchJob : Job? = null

    fun onSearchQueryChanged(query: String){
       searchJob =  viewModelScope.launch {
           cities.value =  repo.searchCities(query)
                 delay(300)
             // the cities will be ready here.

        }
    }

}