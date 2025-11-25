package com.example.contactapp_jan7

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LogInUI() {
    Column (modifier  = Modifier.fillMaxWidth().fillMaxHeight(1f)){
        TextField(placeholder = {Text("Enter Your Name")},
            label = {Text("Log in Info")},
            value = ,
            onValueChange = {

            })
        Button(onClick = {

        }) {
            Text("Log In")
        }
        Text("Welcome ...... ")
    }
}