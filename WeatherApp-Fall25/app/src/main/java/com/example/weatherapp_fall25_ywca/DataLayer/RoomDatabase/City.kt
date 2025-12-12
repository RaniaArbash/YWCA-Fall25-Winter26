package com.example.weatherapp_fall25_ywca.DataLayer.RoomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favcity")
data class City (
    @PrimaryKey(autoGenerate = true) val id: Int,
     val cityName: String,
     val lat: Double,
    val lon: Double
)