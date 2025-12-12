package com.example.weatherapp_fall25_ywca.UILayer.CloudUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp_fall25_ywca.DataLayer.CloudDatabase.CloudFavCity
import org.intellij.lang.annotations.JdkConstants

@Composable
fun CloudFavListScreen( cloudVM : CloudDatabaseViewModel = viewModel()){

    var query by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
            cloudVM.getAllCitiesFromCloudDB()
        }

        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it

                },

                label = { Text("Search city") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            CityTable(
                cloudVM.clouddbList,
                onOneCitySelected = {
                    cloudVM.updateCityInDB(it.docref,44.4,22.2)

                }
            ){
                cloudVM.deleteOneCityFromCLoudDB(it.docref)

            }
        }
    }
@Composable
fun CityTable(
    list: List<CloudFavCity>?,
    onOneCitySelected: (CloudFavCity) -> Unit,
    onDeleteClicked: (CloudFavCity) -> Unit
) {
    LazyColumn {
        items(count = list?.size ?: 0) { index ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // LEFT SIDE (City info)
                Column(
                    modifier = Modifier
                        .clickable { onOneCitySelected(list!![index]) }
                ) {
                    Text(
                        list?.get(index)?.name ?: "",
                        fontSize = 18.sp
                    )

                    Row {
                        Text(
                            "Lon: ${list?.get(index)?.lon}",
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            "Lat: ${list?.get(index)?.lat}",
                            fontSize = 16.sp
                        )
                    }
                }

                // RIGHT SIDE (Delete Button)
                IconButton(onClick = {
                    onDeleteClicked(list!![index])
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete item",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}

