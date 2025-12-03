package com.example.weatherapp_fall25_ywca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp_fall25_ywca.UILayer.CitySearchScreen
import com.example.weatherapp_fall25_ywca.UILayer.WeatherScreen
import com.example.weatherapp_fall25_ywca.ui.theme.WeatherApp_Fall25_YWCATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            WeatherApp_Fall25_YWCATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding).fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween) {
                        MyNavHost(navController)
                    }
                    }
            }
        }
    }
}

@Composable
fun MyNavHost(navController : NavHostController){
    NavHost(navController = navController, startDestination = "citylist"){
        composable("citylist") {
            CitySearchScreen(navController) }
        composable(route="weather/{cityName}") {
            backStakeEntry ->
            val cityName = backStakeEntry.arguments?.getString("cityName") ?:""
            WeatherScreen(cityName)
        }
    }
}

