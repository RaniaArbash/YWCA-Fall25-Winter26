package com.example.weatherapp_fall25_ywca.UILayer.CityUI

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.weatherapp_fall25_ywca.DataLayer.RoomDatabase.CitiesDatabase
import com.example.weatherapp_fall25_ywca.DataLayer.RoomDatabase.CityRepository
import com.example.weatherapp_fall25_ywca.DataLayer.RoomDatabase.CityViewModelFactory
import com.example.weatherapp_fall25_ywca.UILayer.FavCityUI.FavCityViewModel

@Composable
fun CitySearchScreen(navController: NavController, vm: CitiesViewModel = viewModel()) {

    val cities by vm.cities.collectAsState()

    var query by remember { mutableStateOf("") }

    var showAlert = remember { mutableStateOf(false) }
    var selectedCity: String = remember { mutableStateOf("").toString() }

    val context = LocalContext.current
    val database = CitiesDatabase.getDB(context)
    val cityRepo = CityRepository(database.cityDao())
    val factory = CityViewModelFactory(cityRepo)
    val cityVM : FavCityViewModel = viewModel(factory = factory)


    LaunchedEffect (""){
       vm.noSearch()
    }
    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                if (query.length > 2)
                    vm.onSearchQueryChanged(it)
                else
                    vm.noSearch()
            },
            label = { Text("Search city") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(cities
            ) { city ->
                Text(
                    text = city,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp).
                        clickable{
                           // cityVM.saveCity(city,0.0,0.0)
                            //navController.navigate("weather/${city}")
                            selectedCity = city
                            showAlert.value = true
                        }
                )
                if (showAlert.value){
                    AlertComposable(selectedCity,
                        message = "Do You Want To Save ${selectedCity} to DB?",
                        okButton = "Yes, Save",
                        noButton = "NO, Don't Save"
                        , onSave = {
                            cityVM.saveCity(selectedCity,0.0,0.0)
                            navController.navigate("weather/${selectedCity}")
                            showAlert.value = false
                        }, onNotSave = {
                            navController.navigate("weather/${selectedCity}")
                            showAlert.value = false
                        } )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertComposable(city: String,
                    message: String,
                    okButton: String,
                    noButton: String ,
                    onSave: ()->Unit,
                    onNotSave: ()->Unit ){
    AlertDialog(
        modifier = Modifier.background(Color.White),
        onDismissRequest = {}) {
        Column {
            Text(message)
            Row {
                Button(onClick = {
                    onSave()
                }) {
                    Text(okButton)
                }
                Button(onClick = {
                    onNotSave()
                }) {
                    Text(noButton)
                }
            }
        }

    }
}
