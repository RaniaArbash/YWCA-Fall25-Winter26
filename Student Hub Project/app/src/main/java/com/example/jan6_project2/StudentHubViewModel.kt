package com.example.jan6_project2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.Calendar
import java.util.Date

class StudentHubViewModel : ViewModel() {



    val calendar = Calendar.getInstance()

    // Helper function to create dates
    fun createDate(daysFromNow: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, daysFromNow)
        return calendar.time
    }

    // -------------------------------
    // SAMPLE STUDENTS + ASSIGNMENTS
    // -------------------------------
    val sampleStudents = listOf(

        StudentHub(
            name = "Alice Johnson",
            email = "alice.johnson@university.edu",
            ID = "STU001",
            major = "Computer Science",
            GPA = 3.85,
            dailyQuote = "The only way to do great work is to love what you do.",
            notificationList = listOf(
                NotificationClass(
                    message = "Your assignment 'Introduction to Kotlin' is due tomorrow at 11:59 PM.",
                    date = calendar.apply { add(Calendar.HOUR, -1) }.time,
                    isRead = false
                ),
                NotificationClass(
                    message = "New grades have been posted for 'Mobile App Development'.",
                    date = calendar.apply { add(Calendar.HOUR, -1) }.time,
                    isRead = false
                ),
                NotificationClass(
                    message = "Reminder: Office hours today from 2:00 PM - 4:00 PM in Room 305.",
                    date = calendar.apply { add(Calendar.HOUR, -2) }.time,
                    isRead = true
                ),
                NotificationClass(
                    message = "Class cancelled: Android Development lecture on Friday has been rescheduled.",
                    date = calendar.apply { add(Calendar.DAY_OF_MONTH, -1) }.time,
                    isRead = false
                ),
                NotificationClass(
                    message = "New course material uploaded: Week 5 - Jetpack Compose Basics.",
                    date = calendar.apply { add(Calendar.DAY_OF_MONTH, -1) }.time,
                    isRead = true
                ),
                NotificationClass(
                    message = "Your project submission 'Student Portal App' has been received.",
                    date = calendar.apply { add(Calendar.DAY_OF_MONTH, -1) }.time,
                    isRead = true
                ),
                NotificationClass(
                    message = "Upcoming exam: Midterm exam scheduled for December 5th. Review sessions available.",
                    date = calendar.apply { add(Calendar.DAY_OF_MONTH, -1) }.time,
                    isRead = false
                ),
                NotificationClass(
                    message = "Group project teams have been assigned. Check your email for details.",
                    date = calendar.apply { add(Calendar.DAY_OF_MONTH, -1) }.time,
                    isRead = true
                )
            ),
            coursesList = listOf(
                CourseClass("Android Development", "John Doe", 0.75),
                CourseClass("Algorithms", "Prof. Lee", 0.4),
                CourseClass("Databases", "Dr. Smith", 0.2)
            ),
            assignmentList = listOf(
                AssignmentClass(1, "Binary Tree Implementation", createDate(3), false),
                AssignmentClass(2, "SQL Query Project", createDate(7), false),
                AssignmentClass(3, "JavaScript Quiz", createDate(1), true)
            )
        ),

        StudentHub(
            name = "Bob Martinez",
            email = "bob.martinez@university.edu",
            ID = "STU002",
            major = "Mechanical Engineering",
            GPA = 3.42,
            dailyQuote = "Success is not final, failure is not fatal.",
            notificationList = listOf(),
            coursesList = listOf(
                CourseClass("Android Development", "John Doe", 0.75),
                CourseClass("Algorithms", "Prof. Lee", 0.4),
                CourseClass("Databases", "Dr. Smith", 0.2)
            ),
            assignmentList = listOf(
                AssignmentClass(4, "Heat Transfer Problem Set", createDate(5), false),
                AssignmentClass(5, "CAD Design Project", createDate(10), false),
                AssignmentClass(6, "Lab Report #3", createDate(2), true)
            )
        ),

        StudentHub(
            name = "Chen Wei",
            email = "chen.wei@university.edu",
            ID = "STU003",
            major = "Business Administration",
            GPA = 3.91,
            dailyQuote = "Dream big, work hard, stay focused.",
            notificationList = listOf(),
            coursesList = listOf(),
            assignmentList = listOf(
                AssignmentClass(7, "Market Analysis Report", createDate(4), false),
                AssignmentClass(8, "Financial Statement Analysis", createDate(6), false),
                AssignmentClass(9, "Case Study Response", createDate(1), true)
            )
        ),

        StudentHub(
            name = "Diana Okonkwo",
            email = "diana.okonkwo@university.edu",
            ID = "STU004",
            major = "Biology",
            GPA = 3.67,
            dailyQuote = "Science is the poetry of reality.",
            notificationList = listOf(),
            coursesList = listOf(),
            assignmentList = listOf(
                AssignmentClass(10, "Lab Practical Exam", createDate(8), false),
                AssignmentClass(11, "Research Paper Draft", createDate(12), false),
                AssignmentClass(12, "Chapter 5 Quiz", createDate(2), true)
            )
        ),

        StudentHub(
            name = "Ethan O'Brien",
            email = "ethan.obrien@university.edu",
            ID = "STU005",
            major = "Psychology",
            GPA = 3.29,
            dailyQuote = "Understanding others begins with understanding yourself.",
            notificationList = listOf(),
            coursesList = listOf(
                CourseClass("Android Development", "John Doe", 0.75),
                CourseClass("Algorithms", "Prof. Lee", 0.4),
                CourseClass("Databases", "Dr. Smith", 0.2)
            ),
            assignmentList = listOf(
                AssignmentClass(13, "Literature Review", createDate(9), false),
                AssignmentClass(14, "Experiment Design Proposal", createDate(14), false),
                AssignmentClass(15, "Discussion Board Post", createDate(1), true)
            )
        )
    )

    // Holds the currently selected student
    var currentStudent = mutableStateOf<StudentHub?>(sampleStudents[0])
        private set

    // -----------------------------
    //   Toggle assignment completed
    // -----------------------------
    fun toggleAssignmentDone(studentIndex: Int, assignmentId: Int) {

        // Get the student
        val student = sampleStudents[studentIndex]

        // Update only the clicked assignment
        val updatedAssignments = student.assignmentList.map { assignment ->
            if (assignment.id == assignmentId) {
                assignment.copy(isCompleted = !assignment.isCompleted)
            } else assignment
        }

        // Update currentStudent → triggers recomposition
        currentStudent.value = student.copy(assignmentList = updatedAssignments)
    }


    fun markNotificationAsRead(index: Int) {
        currentStudent.value?.let { student ->
            if (index in student.notificationList.indices) {
                student.notificationList[index].isRead = true
                currentStudent.value = currentStudent.value
            }
        }
    }

//    fun markNotificationRead(message: String) {
//        currentStudent.value?.notificationList?.forEach { notification ->
//            if (notification.message == message) {
//                notification.isRead = true
//            }
//        }
//        currentStudent.value = currentStudent.value
//    }
}

// ----------------------------------------
// DATA CLASSES
// ----------------------------------------

data class StudentHub(
    val name: String,
    val email: String,
    val ID: String,
    val major: String,
    val GPA: Double,
    val dailyQuote: String,
    val notificationList: List<NotificationClass>,
    val coursesList: List<CourseClass>,
    val assignmentList: List<AssignmentClass>
)

data class CourseClass(val title: String, val instructor: String, val progress: Double)

data class AssignmentClass(
    val id: Int,
    val title: String,
    val dueDate: Date,
    val isCompleted: Boolean
)

data class NotificationClass(val message: String, val date: Date, var isRead: Boolean)
