package com.example.weatherapp_fall25_ywca.DataLayer


class CitiesRepo{

   suspend fun searchCities(query: String): List<String> {
        if (query.length < 2) return emptyList()
        return CitiesApiService.api.autocompleteCity(query)
    }
}