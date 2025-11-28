package com.example.jan6_project2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.jan6_project2.Navigation.MyNavHost
import com.example.jan6_project2.Scaffold.MyBottomBar
import com.example.jan6_project2.Scaffold.MyTopBar
import com.example.jan6_project2.Scaffold.myFAB

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            Scaffold (
                topBar = { MyTopBar() },
                floatingActionButton = { myFAB() },
                bottomBar = { MyBottomBar { path->
                    navController.navigate(path)
                }}

            ){ innerPadding ->
                Column (modifier = Modifier.padding(innerPadding).fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween) {
                    MyNavHost(navController)
                }
            }

//
        }
    }
}


