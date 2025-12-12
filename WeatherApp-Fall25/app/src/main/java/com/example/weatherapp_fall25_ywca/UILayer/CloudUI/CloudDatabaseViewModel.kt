package com.example.weatherapp_fall25_ywca.UILayer.CloudUI

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp_fall25_ywca.DataLayer.CloudDatabase.CloudDatabaseRepo
import com.example.weatherapp_fall25_ywca.DataLayer.CloudDatabase.CloudFavCity
import kotlinx.coroutines.launch

class CloudDatabaseViewModel: ViewModel() {
    var clouddbList by mutableStateOf<List<CloudFavCity>>(emptyList())
   private var cloudrepo = CloudDatabaseRepo()

    fun getAllCitiesFromCloudDB(){
        viewModelScope.launch {
            clouddbList = cloudrepo.readAllCitiesFromFirestore()
        }
    }

    fun saveCityToCloudDB(name: String, lon: Double, lat: Double){
        viewModelScope.launch {
             cloudrepo.insertCityToFireStorecit(CloudFavCity("",name,lon,lat))
        }

    }

    fun deleteOneCityFromCLoudDB(docID: String){
        viewModelScope.launch {
           cloudrepo.deleteOneCityFromFirestore(docID)
            clouddbList = cloudrepo.readAllCitiesFromFirestore()

        }
    }

    fun updateCityInDB(docID: String, newLat: Double, newLon: Double){
        viewModelScope.launch {
            cloudrepo.updateLocationForCityInFirestore(docID,newLon,newLat)
            clouddbList = cloudrepo.readAllCitiesFromFirestore()

        }
    }


}