package com.example.jan6_project2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    var loggedInName = mutableStateOf("")
    var mycounter by mutableStateOf(0)
    var counter = mutableStateOf(10)
        private set
    // will enforce the UI to rebuild after each update

    var currentColor =   mutableStateOf(Color.White)
        private set
    var uIMode = mutableStateOf(false) // UIMode = light(false) ==> dark (true)
        private set

    fun updateUIMode(updatedSwitch : Boolean){
        uIMode.value = updatedSwitch
        if (uIMode.value){
            currentColor.value = Color.Gray
        }else {
            currentColor.value = Color.White
        }
    }

    fun increase(){
        counter.value++
        mycounter++
    }

    fun decrease(){
        counter.value--
    }

    //
}