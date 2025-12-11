package com.example.weatherapp_fall25_ywca.UILayer.LocationUI

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocationAndWeatherScreen(locationVM: LocationViewModel = viewModel()){

    val location by locationVM.currentLocation.collectAsState()
    val weatherData = locationVM.weatherLocationState.collectAsState()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {granted ->
                  if (granted) {
                      locationVM.fetchLocation()
                  }else {
                      Log.d("Error","No permission")
                  }
            } )

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }


    Column (modifier = Modifier.fillMaxSize()) {
        if (location == null) {
            CircularProgressIndicator()
        } else {
            locationVM.fetchWeatherForLocation(location!!.latitude, location!!.longitude)
            Column(modifier = Modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    fontSize = 25.sp,
                    text = "Lat: ${location!!.latitude}, Lon: ${location!!.longitude}"
                )
                if (weatherData.value == null) {
                    CircularProgressIndicator()
                } else {
                    Text(fontSize = 50.sp, text = weatherData.value?.main?.temp.toString() + "C")
                    Text(
                        fontSize = 35.sp,
                        text = "Feels Like: " + weatherData.value?.main?.feels_like.toString()
                    )
                    weatherData.value?.weather[0]?.description?.let {
                        Text(
                            fontSize = 40.sp,
                            text = it
                        )
                    }
                    MapComposable(modifier = Modifier.fillMaxHeight(),

                        location!!.latitude,
                        location!!.longitude
                    )
                }
            }


        }
    }
    }



@Composable
fun MapComposable(
    modifier: Modifier,
    lat: Double?,
    lon: Double?,
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(lat!!, lon!!), 5f)
    }

    GoogleMap(
        modifier = modifier.fillMaxHeight(0.5f),
        cameraPositionState = cameraPositionState,
    ) {
        Marker(
            state = MarkerState(position = LatLng(lat!!, lon!!)),
            title = "Selected Location"
        )
    }
}