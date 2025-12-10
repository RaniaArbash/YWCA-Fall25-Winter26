package com.example.weatherapp_fall25_ywca.UILayer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun MapScreen(locationVM: LocationViewModel = viewModel()) {

    val weatherData by locationVM.weatherLocationState.collectAsState()

    var markerPosition by remember { mutableStateOf<LatLng?>(null) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(43.65, -79.35), 10f)
    }

    Box(modifier = Modifier.fillMaxSize()){

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLongClick = { latlon ->
                markerPosition = latlon
                locationVM.fetchWeatherForLocation(latlon.latitude, latlon.longitude)

            }
        ) {
           markerPosition.let {
               it?.let { position ->
                   Marker(
                       state = MarkerState(position),
                       title = "Selected Location"
                   )
               }
           }

        }

        weatherData?.let { weather ->
            Card (modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(20.dp)){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(fontSize = 20.sp, text =  "Lat: ${markerPosition?.latitude} Lon: ${markerPosition?.longitude}" )
                    Text(fontSize = 30.sp, text =   weather!!.main.temp.toString()+"C")
                    Text(fontSize = 15.sp, text = "Feels Like: " + weather!!.main.feels_like.toString())
                    Text(fontSize = 20.sp, text = weather!!.weather[0].description)
                }
            }

        }
    }
}