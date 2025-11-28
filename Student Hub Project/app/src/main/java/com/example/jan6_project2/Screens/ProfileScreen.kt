package com.example.jan6_project2.Screens

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jan6_project2.R
import com.example.jan6_project2.StudentHubViewModel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ProfileScreen(viewModel: StudentHubViewModel = viewModel()) {
    Column(Modifier.padding(top = 70.dp).padding(start = 20.dp),
        Arrangement.Center,
        Alignment.Start){
        BasicProfile(Modifier, viewModel)
        Spacer(Modifier.height(50.dp))
        AssignmentList(Modifier, viewModel)
        Spacer(Modifier.height(40.dp))
        NotificationList(Modifier, viewModel)
    }
}


@Composable
fun BasicProfile(modifier: Modifier, viewModel:StudentHubViewModel ) {
    Row (modifier = modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.studentimage),
            contentDescription = "Student Image",
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.width(10.dp))

        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start){


            Spacer(Modifier.width(10.dp))
            // Student name, major, GPA values
            viewModel.currentStudent.value?.let { Text(it.name, fontSize = 40.sp) }
            Text(text = "Major: ${viewModel.currentStudent.value?.major}" , fontSize = 20.sp)
            Text(text = "GPA: ${viewModel.currentStudent.value?.GPA.toString() }", fontSize = 20.sp)
        }
    }

}

@Composable
fun AssignmentList(modifier: Modifier, viewModel:StudentHubViewModel ) {
    var assignments = viewModel.currentStudent.value?.assignmentList ?: emptyList()

    Column(modifier = modifier.fillMaxWidth()) {

        Text("Assignments", fontSize = 30.sp)
        Spacer(Modifier.height(10.dp))


        LazyColumn (modifier = Modifier.fillMaxWidth()){
            // List of assignments variable
            items(assignments) { assignment ->
                Row {
                    Column(){
                        // Assignment name and due status variables
                        Text(assignment.title, fontSize = 20.sp)
                        Text(assignment.dueDate.toString(), fontSize = 15.sp)
                        // Completion status
                        if (assignment.isCompleted){
                            Text("Done", fontSize = 15.sp)
                        }else{
                            Text("Pending", fontSize = 15.sp)
                        }

                    }
                }

                Spacer(Modifier.height(10.dp))
            }

        }
    }

}

@Composable
fun NotificationList(modifier: Modifier, viewModel:StudentHubViewModel ) {

    var notifications = viewModel.currentStudent.value?.notificationList ?: emptyList()

    Column (modifier = modifier.fillMaxWidth()) {

        Text("Notifications", fontSize = 30.sp)
        // Notifications variable unread count
        Text("4 unread notifications", fontSize = 15.sp)
        Spacer(Modifier.height(10.dp))

        LazyColumn (modifier = Modifier.fillMaxWidth()){
            // List of notifications
            items(notifications) { notification ->
                Row {
                    // read status variable
                    if (notification.isRead){
                        Icon(Icons.Default.MailOutline, contentDescription = "menu")
                    }else{
                        Icon(Icons.Default.MailOutline, contentDescription = "menu")
                    }

                    Spacer(Modifier.width(10.dp))

                    // Message and date variables
                    Column(){
                        Text(text = notification.message, fontSize = 20.sp)
                        Text(text = notification.date.toString(), fontSize = 15.sp)
                    }
                }
                Spacer(Modifier.height(10.dp))
            }

        }
    }

}
