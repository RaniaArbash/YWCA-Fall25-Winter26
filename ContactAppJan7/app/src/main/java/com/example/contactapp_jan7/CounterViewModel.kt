package com.example.contactapp_jan7

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    var loggedInName = mutableStateOf("")
    var mycounter by mutableStateOf(0)
    var counter = mutableStateOf(10)
        private set
    // will enforce the UI to rebuild after each update

    fun increase(){
        counter.value++
        mycounter++
    }

    fun decrease(){
        counter.value--
    }

    //
}