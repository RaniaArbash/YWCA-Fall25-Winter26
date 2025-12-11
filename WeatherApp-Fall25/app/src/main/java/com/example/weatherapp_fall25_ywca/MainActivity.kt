package com.example.weatherapp_fall25_ywca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.weatherapp_fall25_ywca.Navigation.MainScaffold
import com.example.weatherapp_fall25_ywca.ui.theme.WeatherApp_Fall25_YWCATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherApp_Fall25_YWCATheme {
              MainScaffold()


            }
        }
    }
}



