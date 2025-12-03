package com.example.weatherapp_fall25_ywca.UILayer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WeatherScreen( cityName: String, wvm : WeatherViewModel = viewModel()){

    val data = wvm.weatherState.collectAsState()

    LaunchedEffect(cityName) {
        wvm.loadWeather(cityName)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        if (data.value == null){
            CircularProgressIndicator()
        }else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(fontSize = 40.sp, text =  cityName )
                Text(fontSize = 50.sp, text =   data.value!!.main.temp.toString()+"C")
                Text(fontSize = 35.sp, text = "Feels Like: " + data.value!!.main.feels_like.toString())

                Text(fontSize = 40.sp, text = data.value!!.weather[0].description)
            }
        }
    }

}