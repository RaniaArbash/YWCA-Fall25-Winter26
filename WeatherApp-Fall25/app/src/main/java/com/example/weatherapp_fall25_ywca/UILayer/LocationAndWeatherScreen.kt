package com.example.weatherapp_fall25_ywca.UILayer

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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

@Composable
fun LocationAndWeatherScreen(locationVM: LocationViewModel = viewModel()){

    val location by locationVM.currentLocation.collectAsState()
    val weatherData = locationVM.weatherLocationState.collectAsState()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {granted ->
                  if (granted) {
                      locationVM.fetchLocation()

                     // location?.let { locationVM.fetchWeatherForLocation(it.latitude, location!!.longitude) }
                  }
            } )

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (location == null) {
            CircularProgressIndicator()
        } else {
            locationVM.fetchWeatherForLocation(location!!.latitude, location!!.longitude)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
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

                }
            }
        }
    }
    }
