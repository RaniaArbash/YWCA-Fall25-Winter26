package com.example.jan6_project2.Scaffold

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.jan6_project2.Navigation.NavItems

@Composable
fun MyBottonBar(onNavigate : (String)->Unit){
    var navItems = listOf(NavItems.calculator, NavItems.counter)
    var selectedIndex = remember { mutableStateOf(0) }
    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                onClick = {
                    selectedIndex.value = index
                    onNavigate(item.path)
                },
                selected = selectedIndex.value == index,
                label = { Text(item.title) },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = "icon")
                }
            )}
    }
}