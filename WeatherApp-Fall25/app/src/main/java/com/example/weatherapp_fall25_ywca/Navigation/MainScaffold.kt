package com.example.weatherapp_fall25_ywca.Navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp_fall25_ywca.UILayer.CitySearchScreen
import com.example.weatherapp_fall25_ywca.UILayer.LocationAndWeatherScreen
import com.example.weatherapp_fall25_ywca.UILayer.WeatherScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather App") }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == ScreenRoutes.LocationAndWeather,
                    onClick = { navController.navigate(ScreenRoutes.LocationAndWeather) },
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Location") },
                    label = { Text("Weather In Location") }
                )
                NavigationBarItem(
                    selected = currentRoute == ScreenRoutes.CitySearch,
                    onClick = { navController.navigate(ScreenRoutes.CitySearch) },
                    icon = { Icon(Icons.Filled.Place, contentDescription = "Search") },
                    label = { Text("City Search") }
                )

            }
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.LocationAndWeather,
        modifier = modifier
    ) {
        // Screen 1 – Popular Destinations
        composable(ScreenRoutes.LocationAndWeather) {
            LocationAndWeatherScreen()
        }

        // Screen 2 – Currency Exchange
        composable(ScreenRoutes.CitySearch) {
            CitySearchStack(navController)
        }

    }
}

@Composable
fun CitySearchStack(navController1 : NavHostController){
    NavHost(navController = navController1, startDestination = "citylist"){
        composable("citylist") {
            CitySearchScreen(navController1) }
        composable(route="weather/{cityName}") {
                backStakeEntry ->
            val cityName = backStakeEntry.arguments?.getString("cityName") ?:""
            WeatherScreen(cityName)
        }
        composable(route="location") {
            LocationAndWeatherScreen()
        }
    }
}