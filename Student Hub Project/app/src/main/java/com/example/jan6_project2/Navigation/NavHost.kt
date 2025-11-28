package com.example.jan6_project2.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jan6_project2.CounterViewModel
import com.example.jan6_project2.Screens.AssignmentScreen
import com.example.jan6_project2.Screens.CoursesScreen
import com.example.jan6_project2.Screens.HomeScreen
import com.example.jan6_project2.Screens.NotificationsScreen
import com.example.jan6_project2.Screens.ProfileScreen

@Composable
fun MyNavHost(navController : NavHostController){
    NavHost(navController = navController,
        startDestination = NavItems.home.path){

        composable(route = NavItems.home.path ) { HomeScreen() }
        composable(route = NavItems.courses.path ) { CoursesScreen() }
        composable(route = NavItems.assignments.path ) { AssignmentScreen() }
        composable(route = NavItems.profile.path ) { ProfileScreen() }
        composable(route = NavItems.notifications.path ) { NotificationsScreen() }

    }
}