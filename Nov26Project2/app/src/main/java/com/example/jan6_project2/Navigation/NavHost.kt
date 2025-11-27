package com.example.jan6_project2.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jan6_project2.CounterViewModel
import com.example.jan6_project2.Screens.CounterComposable
import com.example.jan6_project2.Screens.LogInComposable

@Composable
fun MyNavHost(navController : NavHostController, myVM: CounterViewModel){
    NavHost(navController = navController,
        startDestination = NavItems.calculator.path){

        composable(route = NavItems.calculator.path ) { CounterComposable(myVM) }
        composable(route = NavItems.counter.path ) { LogInComposable(myVM) }

    }
}