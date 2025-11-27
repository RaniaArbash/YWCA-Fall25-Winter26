package com.example.jan6_project2.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun CalculatorScreen (){
    val operationString = remember { mutableStateOf("") }
    // keypad + result text 5 + 6 =
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text(operationString.value, fontSize = 15.sp)
        KeyPad(keyClicked = {key ->
            if (key == "C"){
                operationString.value = ""
            }
            else if (key == "="){}
            else {
                operationString.value += " "
                operationString.value += key
                operationString.value += " "
            }
        })
    }
}

@Composable
fun KeyPad( keyClicked : (String)->Unit){
    Column {
        Row {
            Button (onClick = {keyClicked("1")}) { Text("1") }
            Button (onClick = {keyClicked("2")}) { Text("2") }
            Button (onClick = {keyClicked("3")}) { Text("3") }
            Button (onClick = {keyClicked("+")}) { Text("+") }

        }
        Row {
            Button (onClick = {keyClicked("4")}) { Text("4") }
            Button (onClick = {keyClicked("5")}) { Text("5") }
            Button (onClick = {keyClicked("6")}) { Text("6") }
            Button (onClick = {keyClicked("-")}) { Text("-") }
        }
        Row {
            Button (onClick = {keyClicked("7")}) { Text("7") }
            Button (onClick = {keyClicked("8")}) { Text("8") }
            Button (onClick = {keyClicked("9")}) { Text("9") }
            Button (onClick = {keyClicked("*")}) { Text("*") }

        }

        Row {
            Button (onClick = {keyClicked("C")}) { Text("C") }
            Button (onClick = {keyClicked("0")}) { Text("0") }
            Button (onClick = {keyClicked("=")}) { Text("=") }
            Button (onClick = {keyClicked("/")}) { Text("/") }

        }
    }
}




