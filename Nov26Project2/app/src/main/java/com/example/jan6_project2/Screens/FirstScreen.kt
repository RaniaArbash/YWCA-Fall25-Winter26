package com.example.jan6_project2.Screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jan6_project2.CounterViewModel
import com.example.jan6_project2.R
import com.example.jan6_project2.SecondActivity

@SuppressLint("SuspiciousIndentation")
@Composable
fun LogInComposable (viewModel: CounterViewModel){
    val userInput = remember { mutableStateOf("") }
    val context = LocalContext.current

    Column (modifier = Modifier.fillMaxHeight(1f)
        .fillMaxWidth(1f).background(viewModel.currentColor.value),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(R.drawable.summer),
            contentDescription = "Summer photo", modifier = Modifier.size(100.dp))
        TextField(
            placeholder = { Text("Enter Your Name") },
            label = { Text("Log In Here") },
            value = userInput.value,
            onValueChange = {
                userInput.value = it
            }
        )
        FloatingActionButton(onClick = {
            // val === constant
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("NewName" , userInput.value)
            context.startActivity(intent)
        } , content =  {
            Text("Log In")
        })
        Switch(checked = viewModel.uIMode.value,
            onCheckedChange = {
                viewModel.updateUIMode(it)
            })
    }
}


// UI using jetpack compse toolkit
@Composable
fun CounterComposable(viewModel: CounterViewModel) {
    Column (modifier = Modifier.fillMaxWidth().fillMaxHeight(1f).background(viewModel.currentColor.value),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally ) {
        Text("count ${viewModel.counter.value}",fontSize = 30.sp)// display the counter
        Spacer(modifier = Modifier.height(40.dp))
        Row {
            Button(onClick = {
                // onclick listener
                viewModel.increase()
            }) {
                Text("Increase", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(40.dp))
            Button(onClick = {
                // onclick listener
                viewModel.decrease()
            }) {
                Text("Decrease",fontSize = 20.sp)
            }
        }

    }
}
