package com.example.weatherapp_fall25_ywca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp_fall25_ywca.UILayer.CitySearchScreen
import com.example.weatherapp_fall25_ywca.ui.theme.WeatherApp_Fall25_YWCATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherApp_Fall25_YWCATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CitySearchScreen()
                }
            }
        }
    }
}

