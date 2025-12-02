package com.example.weatherapp_fall25_ywca.UILayer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CitySearchScreen(vm: CitiesViewModel = viewModel()) {

    val cities by vm.cities.collectAsState()

    var query by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = query, onValueChange = {
                query = it
                if (query.length > 2)
                    vm.onSearchQueryChanged(it)
            },
            label = { Text("Search city") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(cities) { city ->
                Text(
                    text = city,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
            }
        }
    }
}
