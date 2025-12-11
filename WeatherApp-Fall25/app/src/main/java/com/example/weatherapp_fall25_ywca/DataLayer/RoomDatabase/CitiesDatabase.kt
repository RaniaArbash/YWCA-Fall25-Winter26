package com.example.weatherapp_fall25_ywca.DataLayer.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [City::class], version = 1)
abstract class CitiesDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDAO

    companion object {
        @Volatile
        private var INSTANCE : CitiesDatabase? = null

        fun getDB(context : Context): CitiesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CitiesDatabase::class.java,
                    "citiesDB"
                    ).build()
                INSTANCE = instance
                instance
            }
        }

    }


}