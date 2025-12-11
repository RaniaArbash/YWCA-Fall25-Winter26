package com.example.weatherapp_fall25_ywca.Navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp_fall25_ywca.UILayer.CityUI.CitySearchScreen
import com.example.weatherapp_fall25_ywca.UILayer.FavCityUI.FavCitiesScreen
import com.example.weatherapp_fall25_ywca.UILayer.LocationUI.LocationAndWeatherScreen
import com.example.weatherapp_fall25_ywca.UILayer.MapScreen
import com.example.weatherapp_fall25_ywca.UILayer.WeatherUI.WeatherScreen

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
                    icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                    label = { Text("City Search") }
                )
                NavigationBarItem(
                    selected = currentRoute == ScreenRoutes.MapScreen,
                    onClick = { navController.navigate(ScreenRoutes.MapScreen) },
                    icon = { Icon(Icons.Filled.Place, contentDescription = "Map") },
                    label = { Text("Google Map") }
                )
                NavigationBarItem(
                    selected = currentRoute == ScreenRoutes.FavCities,
                    onClick = { navController.navigate(ScreenRoutes.FavCities) },
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "FavCities") },
                    label = { Text("Favoriates") }
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
            CitySearchStack()
        }


        // Screen 3 – Map
        composable(ScreenRoutes.MapScreen) {
            MapScreen()
        }

        //Data base
        composable(ScreenRoutes.FavCities) {
            FavCitiesScreen()
        }
    }
}

@Composable
fun CitySearchStack(){
    val stackNavController = rememberNavController()

    NavHost(navController = stackNavController, startDestination = "citylist"){
        composable("citylist") {
            CitySearchScreen(stackNavController) }
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