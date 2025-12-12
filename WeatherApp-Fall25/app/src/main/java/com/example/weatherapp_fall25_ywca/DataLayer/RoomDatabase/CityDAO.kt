package com.example.weatherapp_fall25_ywca.DataLayer.RoomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDAO {

    @Insert
    suspend fun insertNewCity(newCity: City )

    @Query("select * from favcity")
    suspend fun getAllCitiesFromDB() : List<City>

    @Delete
    suspend fun deleteOneCity(todeleteCity: City)

    @Query("select * from favcity where cityName LIKE :startWith || '%'")
    suspend fun searchForCity(startWith: String) : List<City>

   @Query("delete from favcity where cityName LIKE :startWith")
    suspend fun deleteCityStartWith(startWith: String)


}