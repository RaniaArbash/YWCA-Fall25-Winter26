package com.example.jan6_project2.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face

sealed class NavItems {
    object calculator : Item("calc","Calculator", Icons.Default.AccountBox)
    object counter : Item("counter","Counter", Icons.Default.Face)
}