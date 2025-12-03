package com.example.weatherapp_fall25_ywca.DataLayer.CitiesData

import retrofit2.http.GET
import retrofit2.http.Query

interface GeoBytesAPI{// all functions we want
    // api REST == GET, POST, DELETE
    // Get ===> get cites from cities api
    @GET("AutoCompleteCity")
    suspend fun autocompleteCity(@Query("q") query: String):List<String>
}