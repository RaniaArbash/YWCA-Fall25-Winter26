package com.example.weatherapp_fall25_ywca.DataLayer.CloudDatabase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


data class CloudFavCity
    (val docref: String, val name: String,
                         val lon: Double,
                         val lat: Double)


class CloudService() {

    private val firestore: FirebaseFirestore = FirebaseInstance.database
    // add new city to cloud db
    // delete city from cloud db
    // read all cities from cloud db
    // search for city in cloud db


    suspend fun addCityToFirestore(city: CloudFavCity): Boolean{
        val favCityToAdd = hashMapOf(
              "name" to city.name,
            "lat" to city.lat,
            "lon" to city.lon
        )
        // Add a new document with a generated ID
       return try {
            firestore.collection("FavCities")
                .add(favCityToAdd).await()
           true
        }catch (e: Exception){
            Log.d("mydberror",e.toString())
            false
        }
    }

    suspend fun readAllCitiesFromFirestore() : List<CloudFavCity>{
        try {
            val snapshot_AllDocuments = firestore.collection("FavCities").get().await()
            var dblist = emptyList<CloudFavCity>()
            for (doc in snapshot_AllDocuments){
                dblist += CloudFavCity(
                    docref = doc.id,
                    name = doc.data["name"] as String,
                    lon = doc.data["lon"] as Double,
                    lat = doc.data["lat"] as Double
                )
            }
            return dblist
        }catch (e: Exception){
            return emptyList()
        }
    }

    suspend fun deleteOneCityFromFirestore(docID: String): Boolean {
        return try {
            var docRef = firestore.collection("FavCities").document(docID)
            docRef.delete().await()
            true
        }
       catch (e: Exception){
           false
       }
    }



    suspend fun updateLocationForCityInFirestore(docID: String,newlon: Double, newlat: Double): Boolean {
        return try {
            val toupdateCityRef = firestore.collection("FavCities").document(docID)
            toupdateCityRef
                .update(mapOf(
                    "lat" to newlat,
                    "lon" to newlon
                    )).await()

            true
        }
        catch (e: Exception){
            Log.d("error in update", e.toString())
            false
        }
    }
}
