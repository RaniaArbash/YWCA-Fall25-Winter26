package com.example.jan6_project2.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jan6_project2.StudentHubViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun AssignmentScreen(studentVM: StudentHubViewModel = viewModel()) {

    // FORMATTER FOR DATES (ex: Jan 30, 2025)
    val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    // RUN ONLY ONCE → SET DEFAULT STUDENT = Alice (index 0)
//    LaunchedEffect(Unit) {
//        studentVM.setStudent(0)
//    }

    // READ CURRENT STUDENT FROM VIEWMODEL STATE
    val student = studentVM.currentStudent.value

    // SAFETY CHECK → SHOW LOADING UNTIL student IS SET
    if (student == null) {
        Text("Loading...")
        return
    }

    // NOW WE HAVE STUDENT → GET THEIR ASSIGNMENTS
    val assignments = student.assignmentList

    // SHOW A VERTICAL SCROLL LIST
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()      // take full screen
            .padding(16.dp),    // outer spacing
        verticalArrangement = Arrangement.spacedBy(12.dp) // space between cards
    ) {

        // DISPLAY EACH ASSIGNMENT IN A CARD
        items(assignments) { assignment ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()  // full width card
                    .clickable {
                        // WHEN USER TAPS → TOGGLE STATUS IN VIEWMODEL
                        studentVM.toggleAssignmentDone(
                            studentIndex = 0,      // selected student (Alice)
                            assignmentId = assignment.id
                        )
                    },
                elevation = CardDefaults.cardElevation(4.dp) // shadow
            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    // 🔵 ASSIGNMENT TITLE
                    Text(
                        text = assignment.title,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // 🔵 DUE DATE
                    Text(
                        text = "Due: ${formatter.format(assignment.dueDate)}",
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // 🔵 COMPLETED OR PENDING STATUS
                    val statusLabel = if (assignment.isCompleted) "Completed ✔" else "Pending ✘"

                    Text(
                        text = "Status: $statusLabel",
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
