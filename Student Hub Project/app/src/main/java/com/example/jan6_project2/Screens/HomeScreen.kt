package com.example.jan6_project2.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jan6_project2.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Main HomeScreen Composable.
 *
 * Observes the [StudentHubViewModel] to retrieve the current student data.
 * If data is available, delegates rendering to [HomeScreenContent].
 * Otherwise, displays an empty state message.
 *
 * @param studentHubViewModel The ViewModel providing student data.
 */
@Composable
fun HomeScreen(
    studentHubViewModel: StudentHubViewModel = viewModel()
) {
    val student by studentHubViewModel.currentStudent

    student?.let { hub ->
        HomeScreenContent(hub) // Delegate to content function
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No student data available")
        }
    }
}

/**
 * Core layout of the HomeScreen.
 *
 * Displays:
 * 1. Greeting section with student name and daily quote.
 * 2. Summary cards for unread notifications, total courses, and upcoming assignments.
 * 3. Most recent upcoming assignment with closest due date.
 * 4. Lowest-progress courses with instructor details and progress indicators.
 *
 * @param hub The [StudentHub] instance containing student data.
 */
@Composable
fun HomeScreenContent(hub: StudentHub) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Greeting Section
        item {
            Text(
                text = "Welcome , ${hub.name}",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = hub.dailyQuote,
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // 2. Summary Card Section
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SummaryCard("Unread Notifications", hub.notificationList.count { !it.isRead }, Modifier.weight(1f))
                SummaryCard("Courses", hub.coursesList.size, Modifier.weight(1f))
                SummaryCard("Upcoming Assignments", hub.assignmentList.count { !it.isCompleted }, Modifier.weight(1f))
            }
        }

        // 3. Most Recent Assignment Section
        item {
            val upcoming = hub.assignmentList
                .filter { !it.isCompleted }
                .minByOrNull { it.dueDate }

            upcoming?.let { assignment ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Upcoming Assignment", style = MaterialTheme.typography.titleMedium)
                        Text(assignment.title, style = MaterialTheme.typography.bodyLarge)
                        Text(
                            "Due: ${SimpleDateFormat("MMM dd, yyyy").format(assignment.dueDate)}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }

        // 4. Lowest-Progress Courses Section
        item {
            val minProgress = hub.coursesList.minOfOrNull { it.progress } ?: 0.0
            val lowestCourses = hub.coursesList.filter { it.progress == minProgress }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Lowest Progress Courses", style = MaterialTheme.typography.titleMedium)
                lowestCourses.forEach { course ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(course.title, style = MaterialTheme.typography.bodyLarge)
                            Text("Instructor: ${course.instructor}", style = MaterialTheme.typography.bodySmall)
                            LinearProgressIndicator(
                                progress = course.progress.toFloat(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * SummaryCard Composable.
 *
 * Displays a single summary card with a label and numeric value.
 * Used in the Summary Section of the HomeScreen.
 *
 * @param label The title of the summary card (e.g., "Courses").
 * @param value The numeric value to display.
 * @param modifier Modifier for layout customization (e.g., weight in Row).
 */
@Composable
fun SummaryCard(label: String, value: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(label, style = MaterialTheme.typography.bodyMedium)
            Text(value.toString(), style = MaterialTheme.typography.headlineSmall)
        }
    }
}

/**
 * Preview of the HomeScreen layout with mock data.
 *
 * Allows developers to visualize the HomeScreen in Android Studio
 * without requiring a ViewModel or backend data.
// */
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    val mockStudent = StudentHub(
//        name = "Durga",
//        email = "Durga@Test1.com",
//        ID = "12345",
//        major = "Computer Science",
//        GPA = 3.8,
//        dailyQuote = "Keep pushing forward!",
//        notificationList = listOf(
//            NotificationClass("Meeting at 1:30 PM", Date(), false),
//            NotificationClass("Assignment graded", Date(), true),
//            NotificationClass("Meeting at 1:30 PM", Date(), false),
//            NotificationClass("Assignment graded", Date(), true)
//        ),
//        coursesList = listOf(
//            CourseClass("Android", "Rania", 0.5),
//            CourseClass("iOS", "Mr. Satair", 0.8),
//            CourseClass("Databases", "Bindu", 0.5)
//        ),
//        assignmentList = listOf(
//            AssignmentClass("Student Hub", Date(System.currentTimeMillis() + 86400000), false),
//            AssignmentClass("Calculator", Date(System.currentTimeMillis() + 172800000), true)
//        )
//    )
//
//    MaterialTheme {
//        HomeScreenContent(hub = mockStudent)
//    }
//}
