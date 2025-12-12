package com.example.weatherapp_fall25_ywca.DataLayer.CloudDatabase

class CloudDatabaseRepo() {

    val cloudService: CloudService = CloudService()

    suspend fun insertCityToFireStorecit (city :CloudFavCity): Boolean{
      return  cloudService.addCityToFirestore(city )
    }

    suspend fun readAllCitiesFromFirestore() : List<CloudFavCity> {
        return cloudService.readAllCitiesFromFirestore()
    }

    suspend fun deleteOneCityFromFirestore(docID: String): Boolean {
        return cloudService.deleteOneCityFromFirestore(docID)
    }

    suspend fun updateLocationForCityInFirestore(docID: String,newlon: Double, newlat: Double): Boolean {
        return cloudService.updateLocationForCityInFirestore(docID,newlon,newlat)
    }

    }