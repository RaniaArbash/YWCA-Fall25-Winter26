package com.example.jan6_project2.Scaffold

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun myFAB(){
    val context = LocalContext.current
    FloatingActionButton(onClick = {
        Toast.makeText(context,"Fab Clicked", Toast.LENGTH_LONG).show()

    }) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }

}