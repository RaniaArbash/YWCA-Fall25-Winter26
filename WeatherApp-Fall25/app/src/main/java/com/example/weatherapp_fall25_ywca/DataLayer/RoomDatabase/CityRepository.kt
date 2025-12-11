package com.example.weatherapp_fall25_ywca.DataLayer.RoomDatabase

class CityRepository(private val dao: CityDAO) {

    suspend fun addNewCityTODB(city: City) {
        dao.insertNewCity(city)
    }

    suspend fun deleteOneCity(city: City) {
        dao.deleteOneCity(city)
    }

    suspend fun getFavCities(): List<City> {
        return dao.getAllCitiesFromDB()
    }

    suspend fun searchInDB(t: String): List<City> {
        return dao.searchForCity(t)
    }
}