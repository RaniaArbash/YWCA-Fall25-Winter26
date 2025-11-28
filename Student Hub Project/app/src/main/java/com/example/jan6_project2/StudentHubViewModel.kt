package com.example.jan6_project2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.Date

class StudentHubViewModel: ViewModel()  {
    // Repo == source of truth
    var currentStudent = mutableStateOf<StudentHub?>(null)
        private set

    fun setStudent() {
        //currentStudent.value =
    }
    // some functions

}

data  class StudentHub (val name: String,
                       val email :String,
                       val ID :String,
                       val major : String,
                       val GPA : Double,
                       val dailyQuote :String,
                       val notificationList : List<NotificationClass>,
                       val coursesList : List<CourseClass>,
                       val assignmentList : List<AssignmentClass>){
}

data class CourseClass(val title: String, val instructor: String, val progress : Double){ }
data class AssignmentClass(val title: String, val dueDate: Date, val isCompleted: Boolean){ }
data class NotificationClass(val message :String, val date: Date,val isRead: Boolean ){}