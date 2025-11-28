package com.example.jan6_project2.Screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jan6_project2.CourseClass
import com.example.jan6_project2.StudentHubViewModel
import kotlin.collections.emptyList

@Composable
fun CoursesScreen(viewModel: StudentHubViewModel = viewModel()){
    val student by viewModel.currentStudent

    val courses = student?.coursesList ?: emptyList()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Courses",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (courses.isEmpty()) {
            Text(
                text = "No courses available"
            )
        } else {
            LazyColumn {
                items(courses) { course ->
                    CourseItem(course)
                }
            }
        }
    }
}

@Composable
fun CourseItem(course: CourseClass) {
    Card(modifier = Modifier.fillMaxWidth()){
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = course.title)
            Text(text= "Instructor: ${course.instructor}")
            Spacer(modifier=Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(progress = { course.progress.toFloat() }, modifier = Modifier
                    .weight(1f)
                    .height(8.dp))

                Spacer(modifier = Modifier.width(8.dp))

                Text("${(course.progress * 100).toInt()}%")
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun CoursesScreenPreview() {
    CourseItem(
        course = CourseClass(
            title = "Android Development",
            instructor = "John Doe",
            progress = 0.11
        )
    )
}

