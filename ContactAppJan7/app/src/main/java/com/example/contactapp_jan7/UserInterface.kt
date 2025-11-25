package com.example.contactapp_jan7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// UI using jetpack compse toolkit
@Composable
fun UserInterface(viewModel: CounterViewModel) {
    Column (modifier = Modifier.fillMaxWidth().fillMaxHeight(1f),
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


//@Preview(showBackground = true)
//@Composable
//fun UserInterfacePreview() {
//        UserInterface()
//
//}