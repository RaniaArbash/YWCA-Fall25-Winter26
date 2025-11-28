package com.example.jan6_project2.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications

sealed class NavItems {
    object home : Item("home","Home", Icons.Default.Home)
    object courses : Item("courses","Courses", Icons.Default.Info)
    object assignments : Item("assignments","Assignments", Icons.Default.DateRange)
    object profile : Item("profile","Profile", Icons.Default.Face)
    object notifications : Item("notifications","Notifications", Icons.Default.Notifications)

}