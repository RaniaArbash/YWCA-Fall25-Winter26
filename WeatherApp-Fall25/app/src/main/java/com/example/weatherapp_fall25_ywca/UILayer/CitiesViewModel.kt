package com.example.weatherapp_fall25_ywca.UILayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp_fall25_ywca.DataLayer.CitiesData.CitiesRepo
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class CitiesViewModel : ViewModel(){
    private val repo = CitiesRepo()
    private val _cities = MutableStateFlow<List<String>>(emptyList())
    var cities : MutableStateFlow<List<String>> = _cities
    fun onSearchQueryChanged(query: String){
      viewModelScope.launch {
           cities.value =  repo.searchCities(query)// backgroud function // asyn
                 delay(300)
             // the cities will be ready here.

        }
    }
    fun noSearch(){
        cities.value = emptyList()
    }

}