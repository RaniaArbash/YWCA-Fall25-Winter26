package com.example.weatherapp_fall25_ywca.DataLayer.CitiesData

import com.example.weatherapp_fall25_ywca.DataLayer.CitiesData.GeoBytesAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CitiesApiService { // creating the object
    private val BASE_URL = "http://gd.geobytes.com/"
    val api : GeoBytesAPI by lazy {
        Retrofit.Builder().
        baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create())
            .build().create(GeoBytesAPI::class.java)
    }
}
